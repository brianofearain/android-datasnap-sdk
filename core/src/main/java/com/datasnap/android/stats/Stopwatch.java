package com.datasnap.android.stats;

import com.datasnap.android.Logger;

/**
 * A utility class to time an operation, and log it
 * after its ended.
 */
public class Stopwatch {

  private String msg;
  private long start;
  private long end;

  /**
   * Create and start a new timed operation.
   *
   * @param msg Message representing the operation
   */
  public Stopwatch(String msg) {
    this.msg = msg;
    start();
  }

  /**
   * Start the operation
   */
  public void start() {
    start = System.currentTimeMillis();
  }

  /**
   * End the operation and log its result
   */
  public void end() {
    end = System.currentTimeMillis();

    Logger.d("[Stopwatch] %s finished in %d milliseconds.", msg, duration());
  }

  /**
   * Returns the millisecond duration of this operation
   */
  public long duration() {
    return end - start;
  }
}
