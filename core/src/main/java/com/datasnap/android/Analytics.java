package com.datasnap.android;

import android.app.Activity;

import com.google.gson.Gson;
import com.datasnap.android.cache.AnonymousIdCache;
import com.datasnap.android.cache.ISettingsLayer;
import com.datasnap.android.cache.ISettingsLayer.SettingsCallback;
import com.datasnap.android.cache.SettingsCache;
import com.datasnap.android.cache.SettingsThread;
import com.datasnap.android.cache.SimpleStringCache;
import com.datasnap.android.db.IPayloadDatabaseLayer;
import com.datasnap.android.db.IPayloadDatabaseLayer.EnqueueCallback;
import com.datasnap.android.db.PayloadDatabase;
import com.datasnap.android.db.PayloadDatabaseThread;
import com.datasnap.android.events.IEvent;
import com.datasnap.android.flush.FlushThread;
import com.datasnap.android.flush.FlushThread.BatchFactory;
import com.datasnap.android.flush.IFlushLayer;
import com.datasnap.android.flush.IFlushLayer.FlushCallback;
import com.datasnap.android.info.InfoManager;
import com.datasnap.android.models.BasePayload;
import com.datasnap.android.models.Batch;
import com.datasnap.android.models.Context;
import com.datasnap.android.models.EasyJSONObject;
import com.datasnap.android.models.Options;
import com.datasnap.android.models.Props;
import com.datasnap.android.models.Track;
import com.datasnap.android.request.BasicRequester;
import com.datasnap.android.request.IRequestLayer;
import com.datasnap.android.request.IRequester;
import com.datasnap.android.request.RequestThread;
import com.datasnap.android.stats.AnalyticsStatistics;
import com.datasnap.android.utils.HandlerTimer;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.datasnap.android.utils.Utils.isNullOrEmpty;

public final class Analytics {
  private static AnalyticsStatistics statistics;

  private static Context globalContext;
  private static EasyJSONObject bundledIntegrations;

  private static String writeKey;
  private static Config options;

  private static InfoManager infoManager;

  private static HandlerTimer flushTimer;
  private static HandlerTimer refreshSettingsTimer;
  private static PayloadDatabase database;
  private static IPayloadDatabaseLayer databaseLayer;
  private static IRequestLayer requestLayer;
  private static IFlushLayer flushLayer;
  private static ISettingsLayer settingsLayer;

  private static volatile boolean initialized;
  private static volatile boolean optedOut;

  private static SimpleStringCache anonymousIdCache;
  private static SimpleStringCache userIdCache;
  private static SimpleStringCache groupIdCache;
  private static SettingsCache settingsCache;

  private Analytics() {
    throw new AssertionError("No instances allowed");
  }

  public static void onCreate(android.content.Context context) {
    Analytics.initialize(context);
  }

  public static void onCreate(android.content.Context context, String writeKey) {
    Analytics.initialize(context, writeKey);
  }

    // pass in config dynamically
  public static void onCreate(android.content.Context context, String writeKey, Config options) {
    Analytics.initialize(context, writeKey, options);
  }

  public static void activityStart(Activity activity) {
    Analytics.initialize(activity);
  }

  public static void activityStart(Activity activity, String writeKey) {
    Analytics.initialize(activity, writeKey);
  }

  public static void activityStart(Activity activity, String writeKey, Config options) {
    Analytics.initialize(activity, writeKey, options);
    if (optedOut) return;
  }

  /**
   * Called when the activity has been resumed
   *
   * @param activity Your Android Activity
   */
  public static void activityResume(Activity activity) {
    Analytics.initialize(activity);
    if (optedOut) return;
  }

  /**
   * Called when the activity has been stopped
   *
   * @param activity Your Android Activity
   */
  public static void activityStop(Activity activity) {
    Analytics.initialize(activity);
    if (optedOut) return;
  }

  /**
   * Called when the activity has been paused
   *
   * @param activity Your Android Activity
   */
  public static void activityPause(Activity activity) {
    Analytics.initialize(activity);
    if (optedOut) return;
  }


    // main handler....

  public static void initialize(android.content.Context context) {
    if (initialized) return;
    // read both writeKey and options from analytics.xml --- put all options into this file...
    String writeKey = ResourceConfig.getWriteKey(context);
    Config options = ResourceConfig.getOptions(context);
    initialize(context, writeKey, options);
  }

  public static void initialize(android.content.Context context, String writeKey) {
    if (initialized) return;
    // read options from analytics.xml
    Config options = ResourceConfig.getOptions(context);
    initialize(context, writeKey, options);
  }


    // initialize statistics...
    // initialize logging...
    // initialize database..
    // initialize database thread..
    // initialize requester
    // initialize requester thread..
    // initialize and start threads...
    // caches too...
  public static void initialize(android.content.Context context, String writeKey, Config options) {
    String errorPrefix = "analytics-android client must be initialized with a valid ";
    if (context == null) throw new IllegalArgumentException(errorPrefix + "android context.");
   // if (isNullOrEmpty(writeKey)) {
    //  throw new IllegalArgumentException(errorPrefix + "writeKey.");
   // }
    //if (options == null) throw new IllegalArgumentException(errorPrefix + "options.");
    if (initialized) return;

    Analytics.statistics = new AnalyticsStatistics();
    Analytics.writeKey = writeKey;
    Analytics.options = options;
    // set logging based on the debug mode
    Logger.setLog(options.isDebug());

    // create the database using the activity context
    database = PayloadDatabase.getInstance(context);
    // knows how to create global context about this android device
    infoManager = new InfoManager(options);

    anonymousIdCache = new AnonymousIdCache(context);
    groupIdCache = new SimpleStringCache(context, Constants.SharedPreferences.GROUP_ID_KEY);
    userIdCache = new SimpleStringCache(context, Constants.SharedPreferences.USER_ID_KEY);

    // now we need to create our singleton thread-safe database thread
    Analytics.databaseLayer = new PayloadDatabaseThread(database);
    Analytics.databaseLayer.start();

    IRequester requester = new BasicRequester();

    // and a single request thread
    Analytics.requestLayer = new RequestThread(requester);
    Analytics.requestLayer.start();

    // start the flush thread
    Analytics.flushLayer =
        new FlushThread(Analytics.databaseLayer, batchFactory, Analytics.requestLayer);

    Analytics.flushTimer = new HandlerTimer(options.getFlushAfter(), flushClock);

 //   Analytics.refreshSettingsTimer =
  //      new HandlerTimer(options.getSettingsCacheExpiry() + 1000, refreshSettingsClock);

    Analytics.settingsLayer = new SettingsThread(requester);

    settingsCache = new SettingsCache(context, settingsLayer, options.getSettingsCacheExpiry());
    globalContext = generateContext(context);

    initialized = true;

    // start the other threads
    Analytics.flushTimer.start();
  //  Analytics.refreshSettingsTimer.start();
    Analytics.flushLayer.start();
    Analytics.settingsLayer.start();

    // tell the server to look for settings right now
  //  Analytics.refreshSettingsTimer.scheduleNow();
  }

  private static Context generateContext(android.content.Context context) {
    return new Context(infoManager.build(context));
  }

  /**
   * Factory that creates batches from action payloads.
   *
   * Inserts system information into global batches
   */
  private static BatchFactory batchFactory = new BatchFactory() {

    @Override
    public Batch create(List<BasePayload> payloads) {
      return new Batch(writeKey, payloads);
    }
  };

  /**
   * Flushes on a clock timer
   */
  private static Runnable flushClock = new Runnable() {
    @Override
    public void run() {
      Analytics.flush(true);
    }
  };



  //
  // API Calls
  //

  public static void track(IEvent event) {
    track(event, null, null);
  }

  /**
   * Whenever a user performs an event, you'll want to track it.
   *
   * Track will use an automatically generated userId unless one has been
   * provided by identify(..).
   *
   * @param event describes what this user just did. It's a human readable
   * description like "Played a Song", "Printed a Report" or
   * "Updated Status".
   * @param options options allows you to set a timestamp, an anonymousId, a context,
   * and select specific integrations.
   */
  public static void track(IEvent event, Options options) {
    track(event, null, options);
  }

  /**
   * Whenever a user performs an event, you'll want to track it.
   *
   * Track will use an automatically generated userId unless one has been
   * provided by identify(..).
   *
   * @param event describes what this user just did. It's a human readable
   * description like "Played a Song", "Printed a Report" or
   * "Updated Status".
   * @param properties a dictionary with items that describe the event in more
   * detail. This argument is optional, but highly
   * recommended. You'll find these properties extremely useful
   * later.
   */
  public static void track(IEvent event, Props properties) {
    track(event, properties, null);
  }

  /**
   * Whenever a user performs an action, you'll want to track it.
   *
   * Track will use an automatically generated userId unless one has been
   * provided by identify(..).
   *
   * @param event describes what this user just did. It's a human readable
   * description like "Played a Song", "Printed a Report" or
   * "Updated Status".
   * @param properties a dictionary with items that describe the event in more
   * detail. This argument is optional, but highly
   * recommended. You'll find these properties extremely useful
   * later.
   * @param options options allows you to set a timestamp, an anonymousId, a context,
   * and select specific integrations.
   */
  public static void track(IEvent event, Props properties, Options options) {
    checkInitialized();

      Gson gson = new Gson(); // Or use new GsonBuilder().create();
      String json = gson.toJson(event); // serializes target to Json
        if (optedOut) return;

    String userId = getOrSetUserId(null);

    if (isNullOrEmpty(userId)) {
      throw new IllegalArgumentException(
          "analytics-android #track must be initialized with a valid user id.");
    }

    if (isNullOrEmpty(json)) {
      throw new IllegalArgumentException(
          "analytics-android #track must be initialized with a valid event name.");
    }

    if (properties == null) properties = new Props();
    if (options == null) options = new Options();

    Track track = new Track(userId, json, properties, bundledIntegrations, options);
    enqueue(track);
  //  integrationManager.track(track);
    statistics.updateTracks(1);
  }




  //
  // Internal
  //

  /**
   * Gets or sets the current userId. If the provided userId
   * is null, then we'll send the sessionId. If the userId
   * is not null, then it will be set in the userId cache and will
   * be returned.
   */
  private static String getOrSetUserId(String userId) {
    if (isNullOrEmpty(userId)) {
      // no user id provided, lets try to see if we have it saved
      userId = userIdCache.get();
      if (isNullOrEmpty(userId)) {
        // we have no user Id, let's use the sessionId
        userId = anonymousIdCache.get();
      }
    } else {
      // we were passed a user Id so let's save it
      userIdCache.set(userId);
    }

    return userId;
  }


  /**
   * Enqueues an identify
   * {@link com.datasnap.android.models.Track}, alias,
   * or any action of type {@link com.datasnap.android.models.BasePayload}
   */
  public static void enqueue(final BasePayload payload) {
    statistics.updateInsertAttempts(1);
    // merge the global context into this payload's context
    payload.getContext().merge(globalContext);
    final long start = System.currentTimeMillis();
    databaseLayer.enqueue(payload, new EnqueueCallback() {
      @Override
      public void onEnqueue(boolean success, long rowCount) {
        long duration = System.currentTimeMillis() - start;
        statistics.updateInsertTime(duration);
        if (success) {
          Logger.d("Item %s successfully enqueued.", payload.toDescription());
        } else {
          Logger.w("Item %s failed to be enqueued.", payload.toDescription());
        }
       //   flushes depending on rowcount
        if (rowCount >= options.getFlushAt()) {
          Analytics.flush(true);
        }
      }
    });
  }

  private static void checkInitialized() {
    if (!initialized) {
      throw new IllegalStateException("Please call Analytics.initialize before using the library.");
    }
  }

  //
  // Opt out
  //

  /**
   * Turns on opt out, opting out of any analytics sent from this point forward.
   */
  public static void optOut() {
    optOut(true);
  }

  /**
   * Toggle opt out
   *
   * @param optOut true to stop sending any more analytics.
   */
  public static void optOut(boolean optOut) {
    boolean toggled = Analytics.optedOut != optOut;
    Analytics.optedOut = optOut;
  //  if (toggled) integrationManager.toggleOptOut(optOut);
  }

  //
  // Actions
  //

  /**
   * Triggers a flush of data to the server.
   *
   * @param async True to block until the data is flushed
   */
  public static void flush(boolean async) {
    checkInitialized();

    statistics.updateFlushAttempts(1);
    final long start = System.currentTimeMillis();
    final CountDownLatch latch = new CountDownLatch(1);

    flushLayer.flush(new FlushCallback() {
      @Override
      public void onFlushCompleted(boolean success, Batch batch) {
        latch.countDown();
        if (success) {
          long duration = System.currentTimeMillis() - start;
          statistics.updateFlushTime(duration);
        }
      }
    });

    // flush all the integrations as well
   // integrationManager.flush();

    if (!async) {
      try {
        latch.await();
      } catch (InterruptedException e) {
        Logger.e("Interrupted while waiting for a blocking flush.");
      }
    }
  }

  /**
   * Resets the cached userId. Should be used when the user logs out.
   */
  public static void reset() {
    if (initialized) {
      userIdCache.reset();
      groupIdCache.reset();

      // reset all the integrations
    }
  }



  /**
   * Stops the analytics client threads, and resets the client
   */
  public static void close() {
    checkInitialized();

    // stops the looper on the timer, flush, request, and database thread
    flushTimer.quit();
    refreshSettingsTimer.quit();
    flushLayer.quit();
    databaseLayer.quit();
    requestLayer.quit();
    settingsLayer.quit();

    // closes the database
    database.close();

    options = null;
    writeKey = null;

    initialized = false;
  }

  //
  // Getters and Setters
  //

  /**
   * Gets the unique anonymous ID generated for this user
   * until a userId is provided.
   *
   * Use this ID as the "from" ID to alias your new
   * userId if the user was just created.
   */
  public static String getAnonymousId() {
    checkInitialized();
    return anonymousIdCache.get();
  }

  /**
   * Allows you to set your own anonymousId. Used mostly for testing.
   */
  public static void setAnonymousId(String anonymousId) {
    checkInitialized();
    anonymousIdCache.set(anonymousId);
  }

  /**
   * Gets the userId thats currently saved for this
   * application. If none has been entered yet,
   * this will return null.
   */
  public static String getUserId() {
    checkInitialized();
    return userIdCache.get();
  }

  /**
   * Returns whether the client is initialized
   */
  public static boolean isInitialized() {
    return initialized;
  }

  /**
   * Gets the current Segment.io API writeKey
   */
  public static String getWriteKey() {
    if (writeKey == null) checkInitialized();
    return writeKey;
  }

  public static void setWriteKey(String writeKey) {
    Analytics.writeKey = writeKey;
  }



  /**
   * Gets the Segment.io client options
   */
  public static Config getOptions() {
    if (options == null) checkInitialized();
    return options;
  }

  /**
   * Gets the client statistics
   */
  public static AnalyticsStatistics getStatistics() {
    if (statistics == null) checkInitialized();
    return statistics;
  }

  /**
   * Allow custom {link {@link com.datasnap.android.request.IRequester} for counting requests,
   * bandwidth, or testing.
   */
  public static void setRequester(IRequester requester) {
    if (requestLayer instanceof RequestThread) {
      ((RequestThread) requestLayer).setRequester(requester);
    }
  }


}
