package com.vub.scheduler.constraints;

import com.vub.model.Entry;

public class RoomRecorderCV implements ConstraintViolation {
	Entry entry;

	public RoomRecorderCV(Entry entry) {
		this.entry = entry;
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
