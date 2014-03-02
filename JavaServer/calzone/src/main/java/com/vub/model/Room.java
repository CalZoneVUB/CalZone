package com.vub.model;

public class Room {
	String name;
	String building;
	String floor;
	int capacity;
	int places;
	
	boolean hasProjector;
	boolean hasRecorder;
	boolean hasSmartBoard;
	
	@Override
	public String toString() {
		return "Room [name=" + name + ", places=" + places + ", building="
				+ building + ", floor=" + floor + ", capacity=" + capacity
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
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int number) {
		this.capacity = number;
	}

	
	public boolean isProjectorEquipped() {
		return hasProjector;
	}
	
	public void setProjectorEquipped(boolean hasProjector) {
		this.hasProjector = hasProjector;
	}
	
	public boolean isRecorderEquipped() {
		return hasRecorder;
	}
	
	public void setRecorderEquipped(boolean hasRecorder) {
		this.hasRecorder = hasRecorder;
	}
	
	public boolean isSmartBoardEquipped() {
		return hasSmartBoard;
	}
	
	public void setSmartBoardEquipped(boolean hasSmartBoard) {
		this.hasSmartBoard = hasSmartBoard;
	}
	
	public String getClassroomVUBNotation() {
		return getFloor() + getBuilding() + getName();
	}
	
	
}
