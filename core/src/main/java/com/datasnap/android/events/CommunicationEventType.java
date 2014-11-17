package com.datasnap.android.events;

public enum CommunicationEventType {
	COMMUNICATION_DELIVERED, // - A communication has been delivered to the user. It can be associated with a campaign or not. For mobile these are typically push notifications or in App Modals.
	CAMPAIGN_REQUEST, // - The app has requested a campaign to be show to the user.
	CAMPAIGN_REDEMPTION // - The user has redeemed a coupon or similar as part of the campaign.
}
