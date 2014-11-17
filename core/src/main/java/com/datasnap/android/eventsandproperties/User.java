package com.datasnap.android.eventsandproperties;

public class User {

	private Tags tags;
    private PropId Propid;
    private Audience audience;
    private UserProperties userProperties; 
	private boolean optInLocation;    
    private boolean optInPushNotifications;   
    private boolean optInVendor;     
	
	public Tags getTags() {
		return tags;
	}

	public void setTags(Tags tags) {
		this.tags = tags;
	}

	public PropId getId() {
		return Propid;
	}

	public void setId(PropId Propid) {
		this.Propid = Propid;
	}

	public Audience getAudience() {
		return audience;
	}

	public void setAudience(Audience audience) {
		this.audience = audience;
	}

	public UserProperties getUserProperties() {
		return userProperties;
	}

	public void setUserProperties(UserProperties userProperties) {
		this.userProperties = userProperties;
	}
    
    public boolean getOptInLocation() {
		return optInLocation;
	}

	public void setOptInLocation(boolean b) {
		this.optInLocation = b;
	}

	public boolean getOptInPushNotifications() {
		return optInPushNotifications;
	}

	public void setOptInPushNotifications(boolean optInPushNotifications) {
		this.optInPushNotifications = optInPushNotifications;
	}

	public boolean getOptInVendor() {
		return optInVendor;
	}

	public void setOptInVendor(boolean optInVendor) {
		this.optInVendor = optInVendor;
	}


    
}
	
	
