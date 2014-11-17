package com.datasnap.android.events;

//import org.codehaus.jackson.annotate.JsonIgnore;
//import org.codehaus.jackson.map.annotate.JsonSerialize;
//import com.github.datasnap.propertiesandevents.Beacon1;
// import com.github.datasnap.propertiesandevents.DeviceInfo;
// import com.github.datasnap.propertiesandevents.Place;
// import com.github.datasnap.propertiesandevents.User;
import com.datasnap.android.eventsandproperties.Campaign;
import com.datasnap.android.eventsandproperties.Communication;
import com.datasnap.android.eventsandproperties.User;

import java.util.Map;

//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class CommunicationEvent implements IEvent {

	private String eventType;
	private String[] organizationIds;
	private String[] projectIds;
	private User user;
	private Communication communication;
	private Campaign campaign;
	//@JsonIgnore
	private Map<String, Object> additionalProperties;

	public CommunicationEvent(String eventType, String[] organizationIds,
			String[] projectIds, User user, Communication communication,
			Campaign campaign, Map<String, Object> additionalProperties) {
		super();
		this.eventType = eventType;
		this.organizationIds = organizationIds;
		this.projectIds = projectIds;
		this.user = user;
		this.communication = communication;
		this.campaign = campaign;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Communication getCommunication() {
		return communication;
	}

	public void setCommunication(Communication communication) {
		this.communication = communication;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

}
