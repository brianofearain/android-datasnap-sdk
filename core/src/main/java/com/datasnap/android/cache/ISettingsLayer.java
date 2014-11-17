package com.datasnap.android.cache;

import com.datasnap.android.models.EasyJSONObject;
import com.datasnap.android.utils.IThreadedLayer;

/**
 * Handles flushing to the server endpoint
 */
public interface ISettingsLayer extends IThreadedLayer {
  /**
   * Callback for the #get method
   */
  public interface SettingsCallback {

    /**
     * Called when the settings have loaded from the server
     *
     * @param success True for successful flush, false for not.
     */
    void onSettingsLoaded(boolean success, EasyJSONObject object);
  }

  /**
   * Called to flush the queue
   */
  void fetch(SettingsCallback callback);
}
