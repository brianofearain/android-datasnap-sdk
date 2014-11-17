package com.datasnap.android.sample;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.datasnap.android.Analytics;
import com.datasnap.android.TrackedActivity;
import com.datasnap.android.events.BeaconEvent;
import com.datasnap.android.events.IEvent;
import com.datasnap.android.eventsandproperties.Beacon1;
import com.datasnap.android.eventsandproperties.Defaults;
import com.datasnap.android.eventsandproperties.Device;
import com.datasnap.android.eventsandproperties.DeviceInfo;
import com.datasnap.android.eventsandproperties.Place;
import com.datasnap.android.eventsandproperties.PropId;
import com.datasnap.android.eventsandproperties.User;




import java.util.HashMap;
import java.util.Map;

public class MainActivity extends TrackedActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    initViews();
  }

  private void initViews() {
    findViewById(R.id.action_track_a).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
          String[]organizationIds = {Defaults.ORGANISATION_ID};
          String[]projectIds = {Defaults.PROJECT_ID};
          String eventType = "beacon_depart";
          Place place = new Place();
          place.setPlaceId("1234");
          place.setLastPlace("123placeid-3");
          User user = new User();
          PropId propId = new PropId();
          propId.setMobileDeviceIosIdfa("1a847de9f24b18eee3fac634b833b7887b32dea3");
          propId.setGlobalDistinctId("userid1234");
          user.setId(propId);
          Beacon1 beacon = new Beacon1();
          String beaconid = "SHDG-28AHD";
          beacon.setIdentifier(beaconid);
          DeviceInfo deviceInfo = new DeviceInfo();
          deviceInfo.setCreated("2014-08-22 14:48:02 +0000");
          Device device = new Device();
          device.setUserAgent("Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
          device.setIpAddress("127.1.1.1");
          device.setPlatform("ios");
          device.setOsVersion("7.0");
          device.setModel("iPhone5");
          device.setManufacturer("Apple");
          device.setName("hashed device name");
          device.setVendorId("63A7355F-5AF2-4E20-BE55-C3E80D0305B1");
          device.setCarrierName("Verizon");
          device.setCountryCode("1");
          device.setNetworkCode("327");
          deviceInfo.setDevice(device);

          Beacon1 beacon2 = new Beacon1();
          String beaconid2 = "SHDG-test";
          beacon2.setIdentifier(beaconid2);

          Map<String, Object> additionalProperties = new HashMap<String, Object>();
          additionalProperties.put("beacontest", beacon2);
          additionalProperties.put("beacontest2", beacon2);

          IEvent event = new BeaconEvent(eventType, organizationIds, projectIds, place,place, user, beacon,
                  deviceInfo, additionalProperties);
        Analytics.track(event);
      }
    });
    findViewById(R.id.action_track_b).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
    //    Analytics.track("Button B clicked");
      }
    });
    findViewById(R.id.action_track_c).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
      //  Analytics.track("Button C clicked");
      }
    });
    findViewById(R.id.action_track_custom_event).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String event =
            ((EditText) findViewById(R.id.action_custom_event_name)).getText().toString();
        if (isNullOrEmpty(event)) {
          Toast.makeText(MainActivity.this, R.string.name_required, Toast.LENGTH_LONG).show();
        } else {
      //    Analytics.track(event);
        }
      }
    });
    findViewById(R.id.action_flush).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Analytics.flush(true);
      }
    });
    findViewById(R.id.action_test_sequence).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
     //   Analytics.track("The first test event");
       // Analytics.screen("The first test screen");
      //  Analytics.identify("93");
       // Analytics.track("Should have 93 as id");
      //  Analytics.screen("Should also have 93 as id");
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_view_docs) {
      Intent intent = new Intent(Intent.ACTION_VIEW,
        //  Uri.parse("https://segment.io/docs/tutorials/quickstart-android/"));
      try {
        startActivity(intent);
      } catch (ActivityNotFoundException e) {
        Toast.makeText(this, R.string.no_browser_available, Toast.LENGTH_LONG).show();
      }
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /** Returns true if the string is null, or empty (when trimmed). */
  public static boolean isNullOrEmpty(String text) {
    // Rather than using text.trim().length() == 0, use getTrimmedLength to avoid allocating an
    // extra string object
    return TextUtils.isEmpty(text) || TextUtils.getTrimmedLength(text) == 0;
  }
}