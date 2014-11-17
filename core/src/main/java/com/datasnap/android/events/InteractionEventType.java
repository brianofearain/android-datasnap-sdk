package com.datasnap.android.events;

public enum InteractionEventType {

	OPT_IN_LOCATION, // - The user has opted in to track their location and accepted all appropriate terms of use.
	OPT_IN_PUSH_NOTIFICATIONS, // - The user has opted in to receive push notifications.
	OPT_IN_VENDOR, // - A user has opted in to the vendors specific terms.
	APP_INSTALLED, // - The user installed the app
	INTERACTION_TAP, // - User tapped a communication (in app or push notification)
	INTERACTION_VIEW, //  - User viewed something
	INTERACTION_SWIPE, // - User swiped
	INTERACTION_SHAKE, // - user shook the device
	INTERACTION_TILT // - User tilted the devices
	
}
