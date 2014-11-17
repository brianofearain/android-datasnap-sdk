

package com.datasnap.android.flush;

import com.datasnap.android.models.Batch;
import com.datasnap.android.utils.IThreadedLayer;

/**
 * Handles flushing to the server endpoint
 */
public interface IFlushLayer extends IThreadedLayer {

  //
  // Callbacks
  //

  /**
   * Callback for the
   * {@link IFlushLayer#flush(com.datasnap.android.flush.IFlushLayer.FlushCallback)} method
   */
  public interface FlushCallback {
    /**
     * Called when all messages have been flushed from the queue
     *
     * @param success True for successful flush, false for not.
     * @param batch The batch that was sent to the server
     */
    void onFlushCompleted(boolean success, Batch batch);
  }

  //
  // Methods
  //

  /**
   * Triggers a flush from the local action database to the server.
   */
  void flush(FlushCallback callback);
}
