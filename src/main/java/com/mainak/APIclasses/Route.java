package com.mainak.APIclasses;

import java.util.TreeSet;

public class Route {
	private String name="Kolkata Metro Route name";
	private int stationCount;
 	public int getStationCount() {
		return stationCount;
	}
	public void setStationCount(int stationCount) {
		this.stationCount = stationCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private TreeSet<String> stations;
	public TreeSet<String> getStations() {
		return stations;
	}
	public void setStations(TreeSet<String> data) {
		this.stations = data;
	}

}
