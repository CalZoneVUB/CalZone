package com.vub.model;

public class Room {
	String name;
	int places;
	String building;
	String floor;
	int number;
	@Override
	public String toString() {
		return "Room [name=" + name + ", places=" + places + ", building="
				+ building + ", floor=" + floor + ", number=" + number
				+ ", hasProjector=" + hasProjector + ", hasRecorder="
				+ hasRecorder + ", hasSmartBoard=" + hasSmartBoard + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPlaces() {
		return places;
	}
	public void setPlaces(int places) {
		this.places = places;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isHasProjector() {
		return hasProjector;
	}
	public void setHasProjector(boolean hasProjector) {
		this.hasProjector = hasProjector;
	}
	public boolean isHasRecorder() {
		return hasRecorder;
	}
	public void setHasRecorder(boolean hasRecorder) {
		this.hasRecorder = hasRecorder;
	}
	public boolean isHasSmartBoard() {
		return hasSmartBoard;
	}
	public void setHasSmartBoard(boolean hasSmartBoard) {
		this.hasSmartBoard = hasSmartBoard;
	}
	boolean hasProjector;
	boolean hasRecorder;
	boolean hasSmartBoard;
	

}
