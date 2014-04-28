package com.vub.scheduler;

import com.vub.model.Entry;

/**
 * @author pieter
 *
 */
public class StartDateCV extends ConstraintViolation {

	public StartDateCV(Entry entry) {
		super(entry);
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
