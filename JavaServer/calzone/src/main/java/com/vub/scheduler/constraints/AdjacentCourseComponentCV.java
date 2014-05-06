package com.vub.scheduler.constraints;

import com.vub.model.Entry;
import com.vub.utility.DateUtility;

/**
 * @author pieter
 *
 */
public class AdjacentCourseComponentCV implements ConstraintViolation {
	Entry entry1;
	Entry entry2;
	
	public AdjacentCourseComponentCV(Entry entry1, Entry entry2) {
		this.entry1 = entry2;
		this.entry2 = entry2;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry1.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += DateUtility.formatAsDateTime(entry1.getStartingDate());
		msg += " is adjacent with the same course ";
		msg += " given at ";
		msg += DateUtility.formatAsDateTime(entry2.getStartingDate());
		msg += ".";
		
		return msg;
	}
	
}
