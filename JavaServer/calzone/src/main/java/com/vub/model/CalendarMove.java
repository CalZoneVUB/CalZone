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
	private Date newStartDate;
	
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
	public Date getNewStartDate() {
		return newStartDate;
	}
	/**
	 * @param newStartDate the newStartDate to set
	 */
	public void setNewStartDate(Date newStartDate) {
		this.newStartDate = newStartDate;
	}
	
	
}
