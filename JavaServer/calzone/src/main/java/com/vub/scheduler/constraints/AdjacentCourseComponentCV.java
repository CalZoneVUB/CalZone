package com.vub.scheduler.constraints;

import com.vub.model.Entry;

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
		// TODO FINISH!
		String msg = "Course ";
		msg += entry1.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += entry1.getStartingDate().toString();
		msg += " is adjacent with the same course ";
		msg += " given at ";
		msg += entry2.getStartingDate().toString();
		msg += "from traject ";
		return null;
	}
	
}
