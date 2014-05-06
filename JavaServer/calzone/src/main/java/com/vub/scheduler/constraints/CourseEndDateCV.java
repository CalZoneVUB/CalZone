package com.vub.scheduler.constraints;

import com.vub.model.Entry;

public class CourseEndDateCV implements ConstraintViolation {
	Entry entry;
	
	public CourseEndDateCV(Entry entry) {
		this.entry = entry;
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
