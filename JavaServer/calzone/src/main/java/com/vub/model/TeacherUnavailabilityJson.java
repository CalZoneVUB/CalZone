package com.vub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Tim Witters
 * 
 */

public class TeacherUnavailabilityJson {

	private int startingHour;
	private int endingHour;
	/**
	 * @return the startingHour
	 */
	public int getStartingHour() {
		return startingHour;
	}
	/**
	 * @param startingHour the startingHour to set
	 */
	public void setStartingHour(int startingHour) {
		this.startingHour = startingHour;
	}
	/**
	 * @return the endingHour
	 */
	public int getEndingHour() {
		return endingHour;
	}
	/**
	 * @param endingHour the endingHour to set
	 */
	public void setEndingHour(int endingHour) {
		this.endingHour = endingHour;
	}
	
	
}
