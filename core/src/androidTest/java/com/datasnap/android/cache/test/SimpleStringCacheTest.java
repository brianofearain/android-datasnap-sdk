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

package com.datasnap.android.cache.test;

import android.test.AndroidTestCase;
import com.datasnap.android.cache.SimpleStringCache;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class SimpleStringCacheTest extends AndroidTestCase {
  SimpleStringCache cache;

  @Override protected void setUp() throws Exception {
    super.setUp();

    cache = new SimpleStringCache(getContext(), "cache.key");
    cache.reset(); // clear before start
  }

  @Test
  public void testCachePersistence() {
    assertThat(cache.get()).isNull();

    String val = "a cache value";
    cache.set(val);
    assertThat(cache.get()).isEqualTo(val);
  }
}
