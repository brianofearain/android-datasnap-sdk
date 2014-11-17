package com.datasnap.android.eventsandproperties;

//import org.codehaus.jackson.annotate.JsonAutoDetect;
//import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

//@JsonAutoDetect(fieldVisibility=Visibility.ANY)
public class Beacon1 extends Property{

	private String identifier;
	private String bleUuid;
    private String bleVendorUuid;
    private String bleVendorId;
    private String rssi;
    private String isMobile;
    private String previousRssi;
    private String name;
    private String dwellTime;
    private String startTime;
    private String lastUpdateTime;
    private String latitude;
    private String longitude;
    private String visibility;
    private String batteryLevel;
    private String temperature;
    private String hardware;
    private Categories categories;
    private Tags tags;
	
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getBleUuid() {
		return bleUuid;
	}

	public void setBleUuid(String bleUuid) {
		this.bleUuid = bleUuid;
	}

	public String getBleVendorUuid() {
		return bleVendorUuid;
	}

	public void setBleVendorUuid(String bleVendorUuid) {
		this.bleVendorUuid = bleVendorUuid;
	}

	public String getbleVendorId() {
		return bleVendorId;
	}

	public void setBleVendorId(String bleVendorId) {
		this.bleVendorId = bleVendorId;
	}

	public String getRssi() {
		return rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	public String getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}

	public String getPreviousRssi() {
		return previousRssi;
	}

	public void setPreviousRssi(String previousRssi) {
		this.previousRssi = previousRssi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDwellTime() {
		return dwellTime;
	}

	public void setDwellTime(String dwellTime) {
		this.dwellTime = dwellTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(String batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHardware() {
		return hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public Categories getPropCategories() {
		return categories;
	}

	public void setPropCategories(Categories categories) {
		this.categories = categories;
	}

	public Tags getPropTags() {
		return tags;
	}

	public void setPropTags(Tags tags) {
		this.tags = tags;
	}


}
	
	

