/**
 * 
 */
package com.vub.scheduler.constraints;

import com.vub.model.Entry;
import com.vub.utility.DateUtility;

/**
 * @author Pieter Meiresone
 * 
 */
public class CoursePreferedDayOfWeekCV implements ConstraintViolation {
	Entry entry;

	public CoursePreferedDayOfWeekCV(Entry entry) {
		this.entry = entry;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " has a prefered day of ";
		msg += DateUtility.getDayOfWeek(entry.getCourseComponent()
				.getPreferedDayOfWeek());
		msg += " but is given at ";
		msg += DateUtility.getDayOfWeek(DateUtility.getDayOfWeek(entry.getStartingDate()));
		msg += ".";
		
		return msg;
	}

}
