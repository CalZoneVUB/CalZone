package com.vub.scheduler;

import com.vub.model.Entry;

public class RoomRecorderCV extends ConstraintViolation {

	public RoomRecorderCV(Entry entry) {
		super(entry);
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += entry.getStartingDate().toString();
		msg += " requires a recorder which is not available in ";
		msg += entry.getRoom().getDisplayName();
		msg += ".";
		
		return msg;
	}

}
