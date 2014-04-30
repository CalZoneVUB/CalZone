package com.vub.scheduler;

import com.vub.model.Entry;

public class EndDateCV extends ConstraintViolation {

	public EndDateCV(Entry entry) {
		super(entry);
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " is given at ";
		msg += entry.getStartingDate().toString();
		msg += " when course should be ended before ";
		msg += entry.getCourseComponent().getEndingDate().toString();
		msg += ".";
		
		return msg;
	}

}
