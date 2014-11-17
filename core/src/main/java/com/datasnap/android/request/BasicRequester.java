package com.datasnap.android.request;

import android.util.Base64;
import com.datasnap.android.Analytics;
import com.datasnap.android.Config;
import com.datasnap.android.Defaults;
import com.datasnap.android.Logger;
import com.datasnap.android.models.BasePayload;
import com.datasnap.android.models.Batch;
import com.datasnap.android.models.EasyJSONObject;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class BasicRequester implements IRequester {

  @Override
  public HttpResponse send(Batch batch) {
    batch.setSentAt(Calendar.getInstance());

    Config options = Analytics.getOptions();

  // String url = options.getHost() + Defaults.ENDPOINTS.get("import");
   String url = "https://private-anon-f5491adb9-datasnapio.apiary-mock.com/v1.0/events/?api_key=$E9NZuB6A91e2J03PKA2g7wx0629czel8&data=$%2520WERF%2520&redirect=$http%3A%2F%2Fwww.apple.com" ;
      LinkedList<BasePayload> list = (LinkedList<BasePayload>) batch.getBatch();
      BasePayload bp = list.getFirst();
     String js = (String) bp.get("beacon test event");
      String js2 = bp.get("beacon test event").toString();


    HttpClient httpclient = new DefaultHttpClient();
    HttpPost post = new HttpPost(url);
    post.setHeader("Content-Type", "application/json");
    post.addHeader("Authorization",
              "Basic MUVNNTNIVDg1OTdDQzdRNVFQMFU4RE43MzpDY2R1eWFrUnNaOEFRL0hMZFhFUjJFanNDT2xmMjlDVEZWay9CY3RGbVFN");
    try {
      ByteArrayEntity se = new ByteArrayEntity(js.getBytes());
      se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
      post.setEntity(se);
      return httpclient.execute(post);
    } catch (Exception e) {
      Logger.w(e, "Failed to send request.");
    }

    return null;
  }

  @Override
  public EasyJSONObject fetchSettings() {
    Config options = Analytics.getOptions();

    String url = options.getHost() + Defaults.getSettingsEndpoint(Analytics.getWriteKey());

    HttpClient httpclient = new DefaultHttpClient();
    HttpGet get = new HttpGet(url);

    try {
      HttpResponse response = httpclient.execute(get);
      String json = EntityUtils.toString(response.getEntity());
      JSONObject jsonObject = new JSONObject(json);
      return new EasyJSONObject(jsonObject);
    } catch (Exception e) {
      Logger.w(e, "Failed to send request.");
    }

    return null;
  }

  private String basicAuthHeader() {
    return "Basic " + Base64.encodeToString((Analytics.getWriteKey() + ":").getBytes(),
        Base64.NO_WRAP);
  }
}
