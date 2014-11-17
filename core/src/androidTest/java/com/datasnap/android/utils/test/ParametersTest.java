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

package com.datasnap.android.utils.test;

import android.test.AndroidTestCase;
import com.datasnap.android.models.EasyJSONObject;
import com.datasnap.android.utils.Parameters;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ParametersTest extends AndroidTestCase {

  @Test
  public void testMoveNoop() {

    EasyJSONObject json = new EasyJSONObject("hello", 123);

    EasyJSONObject moved = Parameters.move(json, new HashMap<String, String>());

    assertThat(moved).isEqualTo(json);
  }

  @Test
  public void testSimpleMove() {

    EasyJSONObject json = new EasyJSONObject("hello", 123, "firstName", "Ilya");
    EasyJSONObject expected = new EasyJSONObject("hello", 123, "$first_name", "Ilya");

    Map<String, String> map = new HashMap<String, String>();
    map.put("firstName", "$first_name");

    EasyJSONObject moved = Parameters.move(json, map);

    assertThat(moved).isEqualTo(expected);
  }
}
