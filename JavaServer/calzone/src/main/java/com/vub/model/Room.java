package com.vub.model;

public class Room {
	int roomId;
	String name;
	String building;
	int floor;
	int capacity;
	String displayName;
	String institution;
	
	boolean hasProjector;
	boolean hasRecorder;
	boolean hasSmartBoard;
	
	
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", name=" + name + ", building="
				+ building + ", floor=" + floor + ", capacity=" + capacity
				+ ", displayName=" + displayName + ", institution="
				+ institution + ", hasProjector=" + hasProjector
				+ ", hasRecorder=" + hasRecorder + ", hasSmartBoard="
				+ hasSmartBoard + "]";
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public int getFloor() {
		return this.floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
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
	
	
	/** DEPRECATED. Use isProjectorEquipped() */
	public boolean isHasProjector() {
		return hasProjector;
	}
	/** DEPRECATED. Use setProjectorEquipped() */
	public void setHasProjector(boolean hasProjector) {
		this.hasProjector = hasProjector;
	}
	/** DEPRECATED. Use isRecorderEquipped() */
	public boolean isHasRecorder() {
		return hasRecorder;
	}
	/** DEPRECATED. Use setRecorderEquipped() */
	public void setHasRecorder(boolean hasRecorder) {
		this.hasRecorder = hasRecorder;
	}
	/** DEPRECATED. Use isSmartBoardEquipped() */
	public boolean isHasSmartBoard() {
		return hasSmartBoard;
	}
	/** DEPRECATED. Use setSmartBoardEquipped() */
	public void setHasSmartBoard(boolean hasSmartBoard) {
		this.hasSmartBoard = hasSmartBoard;
	}
	/** DEPRECATED. Use getCapacity() */
	public int getNumber() {
		return capacity;
	}
	/** DEPRECATED. Use setCapacity() */
	public void setNumber(int number) {
		this.capacity = number;
	}
	
}
