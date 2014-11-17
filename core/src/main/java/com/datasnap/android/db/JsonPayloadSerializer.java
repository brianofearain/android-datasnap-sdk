
package com.datasnap.android.db;

import com.datasnap.android.Logger;
import com.datasnap.android.events.BeaconEvent;
import com.datasnap.android.models.BasePayload;
import com.datasnap.android.models.Track;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonPayloadSerializer implements IPayloadSerializer {

  @Override
  public String serialize(BasePayload payload) {
    return payload.toString();
  }

  @Override
  public BasePayload deserialize(String str) {
    JSONObject obj;
    try {
      obj = new JSONObject(str);
      String type = obj.getString("type");
     // if (type.equals(Identify.TYPE)) {
     //   return new Identify(obj);
     // }
     if (type.equals(Track.TYPE)) {
        return new Track(obj, new BeaconEvent(null,null,null,null,null,null,null,null,null));
      } /* else if (type.equals(Alias.TYPE)) {
        return new Alias(obj);
      } else if (type.equals(Group.TYPE)) {
        return new Group(obj);
      } else if (type.equals(Screen.TYPE)) {
        return new Screen(obj);
      }*/ else {
        Logger.e("Failed to convert json to base payload because of unknown type: %s", type);
      }
    } catch (JSONException e) {
      Logger.e(e, "Failed to convert json to base payload");
    }

    return null;
  }
}
