package com.datasnap.android.eventsandproperties;

import java.util.ArrayList;

public class Categories extends Property{
	
	private ArrayList<String> sports = new ArrayList<String>();
	private ArrayList<String> womens = new ArrayList<String>(); // dealing with subcategories...
	private ArrayList<String> mens = new ArrayList<String>();
	
	public ArrayList<String> getSports() {
		return sports;
	}
	public void setSports(ArrayList<String> sports) {
		this.sports = sports;
	}
	public ArrayList<String> getWomens() {
		return womens;
	}
	public void setWomens(ArrayList<String> womens) {
		this.womens = womens;
	}
	public ArrayList<String> getMens() {
		return mens;
	}
	public void setMens(ArrayList<String> mens) {
		this.mens = mens;
	}
	

}
