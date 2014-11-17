

package com.datasnap.android.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.datasnap.android.models.EasyJSONObject;
import org.json.JSONObject;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.TELEPHONY_SERVICE;
import static android.net.ConnectivityManager.TYPE_BLUETOOTH;
import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;
import static com.datasnap.android.utils.Utils.getSystemService;
import static com.datasnap.android.utils.Utils.hasPermission;

public class Network implements Info<JSONObject> {

  @Override
  public String getKey() {
    return "network";
  }

  @Override
  public JSONObject get(Context context) {
    EasyJSONObject network = new EasyJSONObject();

    if (hasPermission(context, ACCESS_NETWORK_STATE)) {
      ConnectivityManager connectivityManager = getSystemService(context, CONNECTIVITY_SERVICE);
      if (connectivityManager != null) {
        NetworkInfo wifi = connectivityManager.getNetworkInfo(TYPE_WIFI);
        if (wifi != null) network.put("wifi", wifi.isConnected());
        NetworkInfo bluetooth = connectivityManager.getNetworkInfo(TYPE_BLUETOOTH);
        if (bluetooth != null) network.put("bluetooth", bluetooth.isConnected());
        NetworkInfo cellular = connectivityManager.getNetworkInfo(TYPE_MOBILE);
        if (cellular != null) network.put("cellular", cellular.isConnected());
      }
    }

    TelephonyManager telephonyManager = getSystemService(context, TELEPHONY_SERVICE);
    if (telephonyManager != null) network.put("carrier", telephonyManager.getNetworkOperatorName());

    return network;
  }
}
