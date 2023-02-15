package com.mainak.APIclasses;

import java.util.ArrayList;

public class Ticket {
	private String id;
	private String Source;
	private String Destination;
	private int stationCount;
	private int ticketPrice;
	private int personCount;
	private ArrayList<Station> Stations;
	private String validFrom;
	private String validTo;
	private boolean isReturn;
	
	
	public boolean isReturn() {
		return isReturn;
	}
	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}
	public String getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}
	public String getValidTo() {
		return validTo;
	}
	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}
	public int getPersonCount() {
		return personCount;    
	}
	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public String getId() {   
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public int getStationCount() {
		return stationCount;
	}
	public void setStationCount(int stationCount) {
		this.stationCount = stationCount;
	}
	public int getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public ArrayList<Station> getStations() {
		return Stations;
	}
	public void setStations(ArrayList<String[]> stations) {
		ArrayList<Station> sArr= new ArrayList<Station>();
		for(String[] a: stations) {
			Station sname;
			if(a[1].equals("Y")) {
				sname=new Station(a[0],true);
			}else {
				sname=new Station(a[0],false);
			}
			sArr.add(sname);
			
		}
		this.Stations = sArr;
	}
}

