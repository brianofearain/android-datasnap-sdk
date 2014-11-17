

package com.datasnap.android.info;

import android.content.Context;
import com.datasnap.android.models.EasyJSONObject;
import org.json.JSONObject;

public class Device implements Info<JSONObject> {

  @Override
  public String getKey() {
    return "device";
  }

  @Override
  public JSONObject get(Context context) {
    EasyJSONObject device = new EasyJSONObject();

    device.put("id", android.os.Build.ID);
    device.put("manufacturer", android.os.Build.MANUFACTURER);
    device.put("model", android.os.Build.MODEL);
    device.put("version", android.os.Build.VERSION.SDK_INT);

    return device;
  }
}
