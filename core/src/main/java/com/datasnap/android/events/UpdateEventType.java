package com.datasnap.android.events;

public enum UpdateEventType {
	
	PLACE_UPDATE, //- Indicates that a place update has occurred. Usually would only contain the place.

	CAMPAIGN_UPDATE, // - Indicates that a campaign update has occurred. Usually would only contain the campaign.

	BEACON_UPDATE, // - Indicates that a beacon update has occurred. Usually would only contain the beacon.

	USER_UPDATE, // - Indicates that a beacon update has occurred. Usually would only contain the beacon.

	COMMUNICATION_UPDATE // - Indicates that a beacon update has occurred. Usually would only contain the beacon.
	

}
