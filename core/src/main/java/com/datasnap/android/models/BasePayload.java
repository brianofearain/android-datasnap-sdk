package com.datasnap.android.models;

import java.util.Calendar;
import java.util.UUID;
import org.json.JSONObject;

public class BasePayload extends EasyJSONObject {

  private final static String TYPE_KEY = "type";
  private final static String CONTEXT_KEY = "context";
  private final static String ANONYMOUS_ID_KEY = "anonymousId";
  private final static String TIMESTAMP_KEY = "timestamp";
  private final static String INTEGRATIONS_KEY = "integrations";
  private final static String MESSAGE_ID_KEY = "messageId";

  public BasePayload(JSONObject obj) {
    super(obj);
  }

  public BasePayload(String type, EasyJSONObject bundledIntegrations, Options options) {
    if (options == null) options = new Options();

    setType(type);
    setContext(options.getContext());
    setAnonymousId(options.getAnonymousId());
    setTimestamp(options.getTimestamp());
    setIntegrations(bundledIntegrations, options);
    setMessageId(UUID.randomUUID().toString());
  }

  public String getType() {
    return this.optString(TYPE_KEY, null);
  }

  public void setType(String type) {
    this.put(TYPE_KEY, type);
  }

  public EasyJSONObject getIntegrations() {
    JSONObject object = getObject(INTEGRATIONS_KEY);
    if (object == null) {
      return null;
    } else {
      return (EasyJSONObject) object;
    }
  }

  public void setIntegrations(EasyJSONObject serverIntegrations, Options options) {
    // Top level integrations are used by servers, this is a combination of disabled bundled
    // integrations, and anything the user may have passed in
    putObject(INTEGRATIONS_KEY, generateServerIntegrations(serverIntegrations, options));
    // Context level integrations are used by IntegrationManger, this is simply what the user may
    // have passed in, used to disable integrations for specific events. Could be a bundled one,
    // which we'll skip locally, or a server one, which we'll pass on to the server
    getContext().put(INTEGRATIONS_KEY, options.getIntegrations());
  }

  /**
   * Create a map of integrations for our servers to process. Combines bundled integrations map with
   * user provide options.
   *
   * @param bundledIntegrations Map of integrations sent only through the bundled integrations
   * @param options contains any user defined integrations.
   */
  private static EasyJSONObject generateServerIntegrations(EasyJSONObject bundledIntegrations,
      Options options) {
    EasyJSONObject serverIntegrations = new EasyJSONObject(bundledIntegrations);
    serverIntegrations.merge(options.getIntegrations());
    return serverIntegrations;
  }

  public Context getContext() {
    JSONObject object = getObject(CONTEXT_KEY);
    if (object == null) {
      return null;
    } else {
      return (Context) object;
    }
  }

  public void setContext(Context context) {
    this.put(CONTEXT_KEY, context);
  }

  public String getAnonymousId() {
    return this.optString(ANONYMOUS_ID_KEY, null);
  }

  public void setAnonymousId(String anonymousId) {
    this.put(ANONYMOUS_ID_KEY, anonymousId);
  }

  public Calendar getTimestamp() {
    return getCalendar(TIMESTAMP_KEY);
  }

  public void setTimestamp(Calendar timestamp) {
    super.put(TIMESTAMP_KEY, timestamp);
  }

  public String getMessageId() {
    return this.optString(MESSAGE_ID_KEY, null);
  }

  public void setMessageId(String requestId) {
    this.put(MESSAGE_ID_KEY, requestId);
  }

  /**
   * Gets a simple string description of this payload.
   *
   * @return {String}
   */
  public String toDescription() {
    return String.format("%s [%s]", this.getType(), this.getMessageId());
  }
}