package com.vub.scheduler;

import com.vub.model.Entry;

/**
 * @author pieter
 *
 */
public class AdjacentCourseComponentCV extends ConstraintViolation {
	Entry entry1;
	Entry entry2;
	
	public AdjacentCourseComponentCV(Entry entry1, Entry entry2) {
		super(entry1);
		this.entry2 = entry2;
	}

	@Override
	public String description() {
		// TODO Internationalize
		// TODO FINISH!
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += entry.getStartingDate().toString();
		msg += " is adjacent with the same course ";
		msg += " given at ";
		msg += entry2.getStartingDate().toString();
		msg += "from traject ";
		return null;
	}
	
}
