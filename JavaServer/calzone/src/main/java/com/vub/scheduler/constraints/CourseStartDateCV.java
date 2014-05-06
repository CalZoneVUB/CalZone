package com.vub.scheduler.constraints;

import com.vub.model.Entry;
import com.vub.utility.DateUtility;

/**
 * @author pieter
 * 
 */
public class CourseStartDateCV implements ConstraintViolation {
	Entry entry;

	public CourseStartDateCV(Entry entry) {
		this.entry = entry;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " is given at ";
		msg += DateUtility.formatAsDateTime(entry.getStartingDate());
		msg += " when course should be given after ";
		msg += DateUtility.formatAsDateTime(entry.getCourseComponent()
				.getStartingDate());
		msg += ".";

		return msg;
	}

}
