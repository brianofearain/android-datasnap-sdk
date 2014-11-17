

package com.datasnap.android.info;

import android.content.Context;
import java.text.MessageFormat;

public class Locale implements Info<String> {

  @Override
  public String getKey() {
    return "locale";
  }

  @Override
  public String get(Context context) {
    java.util.Locale l = java.util.Locale.getDefault();
    // IETF BCP 47 language tag representing this locale.
    // equivalent of toLangaugeTag() in JDK7
    // http://docs.oracle.com/javase/7/docs/api/java/util/Locale.html#toLanguageTag()
    return MessageFormat.format("{0}-{1}", l.getLanguage(), l.getCountry());
  }
}
