package com.vub.model;

import java.util.Date;

/**
 * Representing a entry move in the calendar
 * 
 * @author Tim
 *
 */
public class CalendarMoveRoom {
	private int entryId;
	private	int roomId;
	private String message;



	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the entryId
	 */
	public int getEntryId() {
		return entryId;
	}
	/**
	 * @param entryId the entryId to set
	 */
	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}
	/**
	 * @return the roomId
	 */
	public int getRoomId() {
		return roomId;
	}
	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CalendarMoveRoom [entryId=" + entryId + ", roomId=" + roomId
				+ "]";
	}	
}
