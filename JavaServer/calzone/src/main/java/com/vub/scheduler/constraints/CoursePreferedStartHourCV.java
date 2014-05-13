/**
 * 
 */
package com.vub.scheduler.constraints;

import com.vub.model.Entry;
import com.vub.utility.DateUtility;

/**
 * @author pieter
 * 
 */
public class CoursePreferedStartHourCV implements ConstraintViolation {
	Entry entry;

	public CoursePreferedStartHourCV(Entry entry) {
		this.entry = entry;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " has a prefered start hour of ";
		msg += entry.getCourseComponent().getPreferedStartHour(); 
		msg += ":00, but is given at ";
		msg += DateUtility.getHourOfDay(entry
				.getStartingDate());
		msg += ":00.";

		return msg;
	}
}
