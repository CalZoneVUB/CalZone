package com.vub.scheduler.constraints;

import com.vub.model.Entry;

public class RoomTypeCV implements ConstraintViolation {
	Entry entry;

	public RoomTypeCV(Entry entry) {
		this.entry = entry;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += entry.getStartingDate().toString();
		msg += " requires a ";
		msg += entry.getCourseComponent().getRoomTypeRequirement().toString();
		msg += " while room ";
		msg += entry.getRoom().getDisplayName();
		msg += "is a ";
		msg += entry.getRoom().getType().toString();
		msg += ".";
		
		return msg;
	}
}
