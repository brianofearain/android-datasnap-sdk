package com.datasnap.android.eventsandproperties;

public class Geofence {

	private String id;
	private String name;
	private String visibility;
	private Tags tags;
	private GeofenceCircle geofenceCircle;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Tags getPropTags() {
		return tags;
	}

	public void setPropTags(Tags tags) {
		this.tags = tags;
	}

	public GeofenceCircle getGeofenceCircle() {
		return geofenceCircle;
	}

	public void setGeofenceCircle(GeofenceCircle geofenceCircle) {
		this.geofenceCircle = geofenceCircle;
	}

}
