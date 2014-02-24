package com.vub.model;

public class Room {
	String name;
	int places;
	boolean hasProjector;
	boolean hasRecorder;
	boolean hasSmartBoard;
	
	public Room(String _name, int _places, boolean _hasProjector, boolean _hasRecorder, boolean _hasSmartBoard) {
		this.name = _name;
		this.places = _places;
		this.hasProjector = _hasProjector;
		this.hasRecorder = _hasRecorder;
		this.hasSmartBoard = _hasSmartBoard;
	}

	public String getName() {
		return name;
	}

	public int getPlaces() {
		return places;
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
}
