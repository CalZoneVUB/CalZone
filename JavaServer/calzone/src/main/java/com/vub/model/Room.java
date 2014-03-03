package com.vub.model;

public class Room {
	String name;
	int capacity;
	String institution;
	String building;
	int floor;
	int number;
	RoomType type;
	boolean hasProjector;
	boolean hasRecorder;
	boolean hasSmartBoard;
	
	@Override
	public String toString() {
		return "Room [name=" + name + ", capacity=" + capacity
				+ ", institution=" + institution + ", building="
				+ building + ", floor=" + floor + ", number=" + number
				+ ", hasProjector=" + hasProjector + ", hasRecorder="
				+ hasRecorder + ", hasSmartBoard=" + hasSmartBoard
				+ ", type=" + type + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public RoomType getType() {
		return type;
	}
	public void setType(RoomType type) {
		this.type = type;
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
	

}
