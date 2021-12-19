package com.tomveselka.adventofcode2021.objects;

public class DayFifteenVortex {
	private String coord;
	private int distance;
	private String previous;
	
	public String getCoord() {
		return coord;
	}
	
	public void setCoord(String coord) {
		this.coord = coord;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public String getPrevious() {
		return previous;
	}
	
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	
	public DayFifteenVortex(String coord, int distance, String previous) {
		super();
		this.coord = coord;
		this.distance = distance;
		this.previous = previous;
	}
}
