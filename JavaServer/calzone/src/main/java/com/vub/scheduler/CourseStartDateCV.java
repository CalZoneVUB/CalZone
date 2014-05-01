package com.vub.scheduler;

import com.vub.model.Entry;

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
		msg += entry.getStartingDate().toString();
		msg += " when course should be given after ";
		msg += entry.getCourseComponent().getStartingDate().toString();
		msg += ".";
		
		return msg;
	}

}
