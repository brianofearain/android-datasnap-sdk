package com.datasnap.android.events;

//import org.codehaus.jackson.annotate.JsonIgnore;
//import org.codehaus.jackson.map.annotate.JsonSerialize;
//import com.github.datasnap.propertiesandevents.Beacon1;
// import com.github.datasnap.propertiesandevents.DeviceInfo;
// import com.github.datasnap.propertiesandevents.Place;
// import com.github.datasnap.propertiesandevents.User;
import com.datasnap.android.eventsandproperties.Beacon1;
import com.datasnap.android.eventsandproperties.DeviceInfo;
import com.datasnap.android.eventsandproperties.Place;
import com.datasnap.android.eventsandproperties.User;

import java.util.Map;

//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

//Builder Pattern
public class BeaconEventBP implements IEvent{
	private final String eventType;
	private final String[] organizationIds;
	private final String[] projectIds;
	private final Place place;
	private final User user;
	private final Beacon1 beacon;
	private final DeviceInfo deviceInfo; // for now
	//@JsonIgnore
	private Map<String, Object> additionalProperties;
	
	private BeaconEventBP(Builder builder) {
		//Required parameters
		beacon = builder.beacon;
		eventType = builder.eventType;
		// Optional parameters 
		place = builder.place;
		user = builder.user;	
		deviceInfo = builder.deviceInfo;
		additionalProperties = builder.additionalProperties;
		organizationIds = builder.organizationIds;
		projectIds = builder.projectIds;
		
	}
	
	public static class Builder implements IEvent {
		private final String eventType;
		private final Beacon1 beacon;

		private String[] organizationIds;
		private String[] projectIds;
		private Place place;
		private User user;
		private DeviceInfo deviceInfo; // for now
	//	@JsonIgnore
		private Map<String, Object> additionalProperties;




		/* (non-Javadoc)
		 * @see com.github.datasnap.events.IEvent#getAdditionalProperties()
		 */
		

		/**
		 * @param additionalProperties
		 */
		public Builder setAdditionalProperties(Map<String, Object> additionalProperties) {
			this.additionalProperties = additionalProperties;
			return this;

		}



		public Builder organizationIds(String[] val) {
			organizationIds = val;
			return this;

		}


		public Builder projectIds(String[] val) {
			projectIds = val;
			return this;

		}



		public Builder place(Place val) {
			place = val;
			return this;
		}

		
		public Builder deviceInfo(DeviceInfo dev) {
			deviceInfo = dev;
			return this;

		}


		public Builder user(User val) {
			user = val;
			return this;
		}

		public Builder(Beacon1 val, String valstr) {
			this.beacon = val;
			this.eventType = valstr;
		}
		
		public BeaconEventBP build() {
			return new BeaconEventBP(this);
		}


		@Override
		public Map<String, Object> getAdditionalProperties() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public Map<String, Object> getAdditionalProperties() {
		// TODO Auto-generated method stub
		return this.additionalProperties;
	}


}
