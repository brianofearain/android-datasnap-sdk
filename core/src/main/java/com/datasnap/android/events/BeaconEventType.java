package com.datasnap.android.events;

public enum BeaconEventType {
	BEACON_ARRIVED, // A users first time receiving a broadcast from a particular beacon.
	BEACON_SIGHTING, // A user is within a particular beacon range and has received a broadcast from the beacon.
	BEACON_DEPART_VENDOR, // This event can be thrown when a vendors's SDK detect an event. For some vendors this setting can be erroneous so its recommended ot track separately for audit reporting.
	BEACON_DEPART, // A true departed event meaning the user has departed and we feel confident that this event is correct. Some folks use their own calculation to throw this event.
	BEACON_VISIT, // A true user visit into a proximity or geotrigger. This is many times calulated by looking at sighting data or inferred through the use of the departed event.
	BEACON_VISIT_VENDOR // A user visit into a proximity or geotrigger as sent by the beacon vendor. This is used ot compare with the true calculation of the beacon_visit event.

}
