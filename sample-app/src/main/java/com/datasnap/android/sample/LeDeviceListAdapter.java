package com.datasnap.android.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.datasnap.android.Analytics;
import com.datasnap.android.events.BeaconEvent;
import com.datasnap.android.events.IEvent;
import com.datasnap.android.eventsandproperties.Beacon1;
import com.datasnap.android.eventsandproperties.Categories;
import com.datasnap.android.eventsandproperties.Defaults;
import com.datasnap.android.eventsandproperties.Device;
import com.datasnap.android.eventsandproperties.DeviceInfo;
import com.datasnap.android.eventsandproperties.Place;
import com.datasnap.android.eventsandproperties.PropId;
import com.datasnap.android.eventsandproperties.Tags;
import com.datasnap.android.eventsandproperties.User;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class LeDeviceListAdapter extends BaseAdapter {

  private ArrayList<Beacon> beacons;
  private LayoutInflater inflater;

  public LeDeviceListAdapter(Context context) {
    this.inflater = LayoutInflater.from(context);
      Analytics.initialize(context);
      this.beacons = new ArrayList<Beacon>();
  }

  public void replaceWith(Collection<Beacon> newBeacons) {
    this.beacons.clear();
    this.beacons.addAll(newBeacons);
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return beacons.size();
  }

  @Override
  public Beacon getItem(int position) {
    return beacons.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {
    view = inflateIfRequired(view, position, parent);
    bind(getItem(position), view);
    return view;
  }

  private void bind(Beacon beaconn, View view) {
    ViewHolder holder = (ViewHolder) view.getTag();
//    holder.macTextView.setText(String.format("MAC: %s (%.2fm)", beaconn.getMacAddress(), Utils.computeAccuracy(beaconn)));
    holder.majorTextView.setText("Major: " + beaconn.getMajor());
    holder.minorTextView.setText("Minor: " + beaconn.getMinor());
    holder.measuredPowerTextView.setText("MPower: " + beaconn.getMeasuredPower());
    holder.rssiTextView.setText("RSSI: " + beaconn.getRssi());


      String[]organizationIds = {Defaults.ORGANISATION_ID};
      String[]projectIds = {Defaults.PROJECT_ID};
      String eventType = "beacon_sighting";

      User user = new User();
      PropId propId = new PropId();
      propId.setMobileDeviceIosIdfa("1a847de9f24b18eee3fac634b833b7887b32dea3");
      propId.setGlobalDistinctId("userid1234");
      user.setId(propId);

      Place majorPlace = new Place();
      majorPlace.setName("major");
      majorPlace.setPlaceId(""+beaconn.getMajor());


      Place minorPlace = new Place();
      minorPlace.setName("major");
      minorPlace.setPlaceId(""+beaconn.getMinor());


      Beacon1 beacon1 = new Beacon1();
      beacon1.setRssi(""+beaconn.getRssi());

      beacon1.setBatteryLevel("" + beaconn.getMeasuredPower());
      beacon1.setHardware(beaconn.getMacAddress());
      beacon1.setBleUuid(beaconn.getProximityUUID());
      beacon1.setIdentifier(beaconn.getName());
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

      IEvent event = new BeaconEvent(eventType, organizationIds, projectIds, majorPlace, minorPlace, user, beacon1,
              deviceInfo, additionalProperties);
      Analytics.track(event);
  }

  private View inflateIfRequired(View view, int position, ViewGroup parent) {
    if (view == null) {
      view = inflater.inflate(R.layout.device_item, null);
      view.setTag(new ViewHolder(view));
    }
    return view;
  }

  static class ViewHolder {
    final TextView beaconSightEvent;
  //  final TextView macTextView;
    final TextView majorTextView;
    final TextView minorTextView;
    final TextView measuredPowerTextView;
    final TextView rssiTextView;

    ViewHolder(View view) {
      beaconSightEvent = (TextView) view.findViewWithTag("event");
   //   macTextView = (TextView) view.findViewWithTag("mac");
      majorTextView = (TextView) view.findViewWithTag("major");
      minorTextView = (TextView) view.findViewWithTag("minor");
      measuredPowerTextView = (TextView) view.findViewWithTag("mpower");
      rssiTextView = (TextView) view.findViewWithTag("rssi");
    }
  }
}
