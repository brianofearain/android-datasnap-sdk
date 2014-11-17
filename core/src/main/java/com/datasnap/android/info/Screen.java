

package com.datasnap.android.info;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.datasnap.android.models.EasyJSONObject;
import org.json.JSONObject;

import static com.datasnap.android.utils.Utils.getSystemService;

public class Screen implements Info<JSONObject> {

  @Override
  public String getKey() {
    return "screen";
  }

  @Override
  public JSONObject get(Context context) {
    EasyJSONObject screen = new EasyJSONObject();
    WindowManager manager = getSystemService(context, Context.WINDOW_SERVICE);
    if (manager != null) {
      DisplayMetrics metrics = new DisplayMetrics();

      android.view.Display display = manager.getDefaultDisplay();
      display.getMetrics(metrics);

      screen.put("height", metrics.heightPixels);
      screen.put("width", metrics.widthPixels);
      screen.put("density", metrics.density);
    }
    return screen;
  }
}
