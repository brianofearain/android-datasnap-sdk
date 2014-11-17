

package com.datasnap.android.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.datasnap.android.models.EasyJSONObject;
import org.json.JSONObject;

public class App implements Info<JSONObject> {

  @Override
  public String getKey() {
    return "app";
  }

  @Override
  public JSONObject get(Context context) {

    EasyJSONObject app = new EasyJSONObject();

    PackageInfo packageInfo;
    try {
      packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

      app.put("name", packageInfo.applicationInfo.name);
      app.put("version", packageInfo.versionName);
      app.put("build", packageInfo.packageName);
    } catch (NameNotFoundException e) {
    }

    return app;
  }
}
