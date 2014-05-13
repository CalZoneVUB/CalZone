package com.vub.scheduler.constraints;

import com.vub.model.Entry;
import com.vub.utility.DateUtility;

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
		msg += DateUtility.formatAsDateTime(entry.getStartingDate());
		msg += " when course should be ended before ";
		msg += DateUtility.formatAsDateTime(entry.getCourseComponent().getEndingDate());
		msg += ".";
		
		return msg;
	}

}
