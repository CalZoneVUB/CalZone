package com.vub.model;

public class Room {
	String building;
	int floor;
	int roomNumber;
	int places;
	RoomType roomType;
	boolean hasProjector;
	boolean hasRecorder;
	boolean hasSmartBoard;

	public int getPlaces() {
		return places;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public boolean isHasProjector() {
		return hasProjector;
	}

	public boolean isHasRecorder() {
		return hasRecorder;
	}

	public boolean isHasSmartBoard() {
		return hasSmartBoard;
	}

	public String getBuilding() {
		return building;
	}

	public int getFloor() {
		return floor;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	@Override
	public String toString() {
		return "Room [building=" + building + ", floor=" + floor
				+ ", roomNumber=" + roomNumber + ", places=" + places
				+ ", roomType=" + roomType + ", hasProjector=" + hasProjector
				+ ", hasRecorder=" + hasRecorder + ", hasSmartBoard="
				+ hasSmartBoard + "]";
	}
	
}
