package com.mainak.APIclasses;

public class Station {
	private String stationName;
	private Boolean isTerminal;
	
	public Station(String stationName, Boolean isTerminal) {
		super();
		this.stationName = stationName;
		this.isTerminal = isTerminal;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Boolean getIsTerminal() {
		return isTerminal;
	}
	public void setIsTerminal(Boolean isTerminal) {
		this.isTerminal = isTerminal;
	}
	
}
