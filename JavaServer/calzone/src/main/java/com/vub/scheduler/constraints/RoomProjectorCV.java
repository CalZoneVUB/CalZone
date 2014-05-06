package com.vub.scheduler.constraints;

import com.vub.model.Entry;

public class RoomProjectorCV implements ConstraintViolation {
	Entry entry;

	public RoomProjectorCV(Entry entry) {
		this.entry = entry;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += entry.getStartingDate().toString();
		msg += " requires a projector which is not available in ";
		msg += entry.getRoom().getDisplayName();
		msg += ".";
		
		return msg;
	}

}
