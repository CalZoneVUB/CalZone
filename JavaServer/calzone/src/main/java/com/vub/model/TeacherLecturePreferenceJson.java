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
 * Class that represents the ajax call comming in to make atecherLecturePreference Object
 * 
 * @author Tim Witters
 * 
 */
public class TeacherLecturePreferenceJson {

	private int courseComponentId;
	private int dayOfWeek;
	private int startingHour;
	private int endingHour;
	
	/**
	 * @return the courseComponentId
	 */
	public int getCourseComponentId() {
		return courseComponentId;
	}
	/**
	 * @param courseComponentId the courseComponentId to set
	 */
	public void setCourseComponentId(int courseComponentId) {
		this.courseComponentId = courseComponentId;
	}
	/**
	 * @return the dayOfWeek
	 */
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
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
