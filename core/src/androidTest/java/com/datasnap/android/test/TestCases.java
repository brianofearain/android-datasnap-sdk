/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Segment.io, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.datasnap.android.test;

import com.datasnap.android.models.BasePayload;
import com.datasnap.android.models.Batch;
import com.datasnap.android.models.Context;
import com.datasnap.android.models.EasyJSONObject;
import com.datasnap.android.models.Options;
import com.datasnap.android.models.Props;
import com.datasnap.android.models.Track;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;
import java.util.TimeZone;

@SuppressWarnings("serial")
public final class TestCases {

  private static Random random = new Random();

  private TestCases() {
    throw new AssertionError("No instances");
  }

  public static Calendar calendar() {
    Calendar calendar = new GregorianCalendar();
    calendar.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
    calendar.set(Calendar.MILLISECOND, (int) (System.currentTimeMillis() + random.nextInt(10000)));
    return calendar;
  }

  static EasyJSONObject createBundledIntegrations() {
    return new EasyJSONObject();
  }

  public static Identify identify() {
    return new Identify("ilya@segment.io",
        new Traits("name", "Achilles", "email", "achilles@segment.io", "subscriptionPlan",
            "Premium", "friendCount", 29, "company",
            new EasyJSONObject().put("name", "Company, inc.")), createBundledIntegrations(),
        new Options().setTimestamp(calendar()).setContext(new Context().put("ip", "192.168.1.1"))
    );
  }

  public static Group group() {
    return new Group("ilya@segment.io", "segmentio_id",
        new Traits("name", "Segment.io", "plan", "Premium"), createBundledIntegrations(),
        new Options().setTimestamp(calendar()).setContext(new Context("ip", "192.168.1.1")));
  }

  public static Track track() {
    return new Track("ilya@segment.io", "Played a Song on Android",
        new Props("name", "Achilles", "revenue", 39.95, "shippingMethod", "2-day"),
        createBundledIntegrations(), new Options().setTimestamp(calendar())
        .setContext(new Context("ip", "192.168.1.1"))
        .setIntegration("Mixpanel", false)
        .setIntegration("KISSMetrics", true)
        .setIntegration("Google Analytics", true)
    );
  }

  public static Screen screen() {
    return new Screen("ilya@segment.io", "Login Page", "Authentication",
        new Props("logged-in", true, "type", "teacher"), createBundledIntegrations(),
        new Options().setTimestamp(calendar())
            .setContext(new Context("ip", "192.168.1.1"))
            .setIntegration("Mixpanel", false)
            .setIntegration("KISSMetrics", true)
            .setIntegration("Google Analytics", true)
    );
  }

  public static Alias alias() {
    return new Alias("from", "to", createBundledIntegrations(), null);
  }

  public static Batch batch(String writeKey) {
    return new Batch(writeKey, new LinkedList<BasePayload>() {{
      this.add(identify());
      this.add(track());
      this.add(alias());
    }});
  }

  public static BasePayload random() {
    switch (random.nextInt(5)) {
      case 0:
        return identify();
      case 1:
        return track();
      case 2:
        return group();
      case 3:
        return screen();
      default:
        return alias();
    }
  }
}
