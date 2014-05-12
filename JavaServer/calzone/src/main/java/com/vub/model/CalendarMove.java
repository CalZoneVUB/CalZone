package com.vub.model;

import java.util.Date;

/**
 * Representing a entry move in the calendar
 * 
 * @author Tim
 *
 */
public class CalendarMove {
	private int entryId;
	private long newStartDate;
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
	 * @return the newStartDate
	 */
	public long getNewStartDate() {
		return newStartDate;
	}
	/**
	 * @param newStartDate the newStartDate to set
	 */
	public void setNewStartDate(long newStartDate) {
		this.newStartDate = newStartDate;
	}
	
	
}
