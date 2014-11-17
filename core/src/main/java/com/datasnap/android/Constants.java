package com.datasnap.android;

public class Constants {
  public static final String PACKAGE_NAME = Constants.class.getPackage().getName();

  /**
   * The maximum amount of events to flush at a time
   */
  public static final int MAX_FLUSH = 20;

  public static class Database {

    /**
     * Version 1: uses payload.action
     * Version 2: uses payload.type
     */
    public static final int VERSION = 2;

    public static final String NAME = PACKAGE_NAME;

    public static class PayloadTable {

      public static final String NAME = "payload_table";

      public static final String[] FIELD_NAMES = new String[] {
          Fields.Id.NAME, Fields.Payload.NAME
      };

      public static class Fields {

        public static class Id {

          public static final String NAME = "id";

          /**
           * INTEGER PRIMARY KEY AUTOINCREMENT means index is monotonically
           * increasing, regardless of removals
           */
          public static final String TYPE = "INTEGER PRIMARY KEY AUTOINCREMENT";
        }

        public static class Payload {

          public static final String NAME = "payload";

          public static final String TYPE = " TEXT";
        }
      }
    }
  }

  public class SharedPreferences {
    public static final String ANONYMOUS_ID_KEY = "anonymous.id";
    public static final String USER_ID_KEY = "user.id";
    public static final String GROUP_ID_KEY = "group.id";
  }
}
