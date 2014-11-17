package com.datasnap.android.eventsandproperties;

public class DeviceInfo extends Property{
	
	public String created;
    public Device device;
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

}
