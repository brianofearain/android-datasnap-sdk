package com.datasnap.android.events;

//import org.codehaus.jackson.annotate.JsonIgnore;
//import org.codehaus.jackson.map.annotate.JsonSerialize;
//import com.github.datasnap.propertiesandevents.Beacon1;
// import com.github.datasnap.propertiesandevents.DeviceInfo;
// import com.github.datasnap.propertiesandevents.Place;
// import com.github.datasnap.propertiesandevents.User;
import com.datasnap.android.eventsandproperties.Beacon1;
import com.datasnap.android.eventsandproperties.Place;
import com.datasnap.android.eventsandproperties.User;

import java.util.Map;

//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class UpdateEvent implements IEvent {

	// different types of updates- might link to other event types....

	private String eventType;
	private String[] organizationIds;
	private String[] projectIds;
	private Beacon1 Beacon;
	private Place place;
	private User user;
	//@JsonIgnore
	private Map<String, Object> additionalProperties;

	public UpdateEvent(String eventType, String[] organizationIds,
			String[] projectIds, Beacon1 beacon, Map<String, Object> additionalProperties) {
		super();
		this.eventType = eventType;
		this.organizationIds = organizationIds;
		this.projectIds = projectIds;
		Beacon = beacon;
		this.additionalProperties = additionalProperties;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public String getEvent_type() {
		return eventType;
	}

	public void setEvent_type(String eventType) {
		this.eventType = eventType;
	}

	public String[] getOrganizationIds() {
		return organizationIds;
	}

	public void setOrganizationIds(String[] organizationIds) {
		this.organizationIds = organizationIds;
	}

	public String[] getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String[] projectIds) {
		this.projectIds = projectIds;
	}

	public Beacon1 getBeacon() {
		return Beacon;
	}

	public void setBeacon(Beacon1 Beacon) {
		this.Beacon = Beacon;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
