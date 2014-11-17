package com.datasnap.android.eventsandproperties;

import java.math.BigDecimal;

public class Location {

	public Location(BigDecimal[] coordinates) {
		super();
		this.coordinates = coordinates;
	}

	private BigDecimal[] coordinates;

	public BigDecimal[] getCoordinates() {
		return coordinates;
	}

}
