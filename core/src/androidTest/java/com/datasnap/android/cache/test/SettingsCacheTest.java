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

import android.content.Context;
import com.datasnap.android.cache.SettingsCache;
import com.datasnap.android.cache.SettingsThread;
import com.datasnap.android.request.BasicRequester;
import com.datasnap.android.request.IRequester;
import com.datasnap.android.test.BaseTest;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class SettingsCacheTest extends BaseTest {

  private static SettingsCache cache;
  private static IRequester requester;
  private static int cacheForMs;

  @BeforeClass
  protected void setUp() {
    super.setUp();

    Context context = this.getContext();
    requester = new BasicRequester();
    cacheForMs = 1000;
    SettingsThread thread = new SettingsThread(requester);
    thread.start();
    cache = new SettingsCache(context, thread, cacheForMs);
    // resets the cache by removing the settings
    cache.reset();
  }

  @Test
  public void loadTest() {
    int reloads = cache.getReloads();

    assertThat(reloads).isEqualTo(cache.getReloads());
    assertThat(cache.get()).isNotNull();

    assertThat(reloads + 1).isEqualTo(cache.getReloads());
    assertThat(cache.getSettings()).isNotNull();
    assertThat(reloads + 1).isEqualTo(cache.getReloads());

    assertThat(cache.get()).isNotNull();
    assertThat(reloads + 1).isEqualTo(cache.getReloads());

    assertThat(cache.getSettings()).isNotNull();
    assertThat(reloads + 1).isEqualTo(cache.getReloads());
  }

	
	/* TODO:  Test needs to be fixed
  @Test
	public void testRefreshTest() {
		
		int reloads = cache.getReloads();
		
		Assert.assertEquals(reloads, cache.getReloads());
		Assert.assertNotNull(cache.get());
		Assert.assertEquals(reloads + 1, cache.getReloads());
		
		try {
			Thread.sleep(cacheForMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(reloads + 1, cache.getReloads());
		Assert.assertNotNull(cache.getSettings());
		Assert.assertEquals(reloads + 2, cache.getReloads());
	}
	*/
}
