package com.datasnap.android;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Defaults {
  public static final boolean DEBUG = false;

  public static final String HOST = "https://api-events.datasnap.io/v1.0/";

  public static final int FLUSH_AT = 20;
  public static final int FLUSH_AFTER = (int) TimeUnit.SECONDS.toMillis(10);

  @SuppressWarnings("serial")
  public static final Map<String, String> ENDPOINTS = new HashMap<String, String>() {
    {
     // this.put("identify", "/v1/identify");
      // this.put("alias", "/v1/alias");
      this.put("track", "/v1/track");
      this.put("import", "/v1/import");
    }
  };

  public static String getSettingsEndpoint(String writeKey) {
    return "/project/" + writeKey + "/settings";
  }

  public static final int MAX_QUEUE_SIZE = 10000;

  // cache the settings for 1 hour before reloading
  public static final int SETTINGS_CACHE_EXPIRY = 1000 * 60 * 60;

  // try to send the location by default
  public static final boolean SEND_LOCATION = true;
}