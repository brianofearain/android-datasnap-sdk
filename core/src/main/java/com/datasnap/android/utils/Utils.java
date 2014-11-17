package com.datasnap.android.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.Collection;

public final class Utils {
  private Utils() {
    throw new AssertionError("No instances");
  }

  /** Returns true if the application has the given permission. */
  public static boolean hasPermission(Context context, String permission) {
    return context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
  }

  /** Returns true if the application has the given feature. */
  public static boolean hasFeature(Context context, String feature) {
    return context.getPackageManager().hasSystemFeature(feature);
  }

  /** Returns the system service for the given string. */
  @SuppressWarnings("unchecked")
  public static <T> T getSystemService(Context context, String serviceConstant) {
    return (T) context.getSystemService(serviceConstant);
  }

  /** Returns true if the string is null, or empty (when trimmed). */
  public static boolean isNullOrEmpty(String text) {
    // Rather than using text.trim().length() == 0, use getTrimmedLength to avoid allocating an
    // extra string object
    return TextUtils.isEmpty(text) || TextUtils.getTrimmedLength(text) == 0;
  }

  /** Returns true if the collection is null, or empty. */
  public static boolean isCollectionNullOrEmpty(Collection collection) {
    // Rather than using text.trim().length() == 0, use getTrimmedLength to avoid allocating an
    // extra string object
    return collection == null || collection.size() == 0;
  }
}
