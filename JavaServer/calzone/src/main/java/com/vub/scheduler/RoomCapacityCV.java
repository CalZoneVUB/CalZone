package com.vub.scheduler;

import com.vub.model.Entry;

public class RoomCapacityCV extends ConstraintViolation {
	public RoomCapacityCV(Entry entry) {
		super(entry);
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Room capacity violation for course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " at ";
		msg += entry.getStartingDate().toString();
		msg += " in room ";
		msg += entry.getRoom().getDisplayName();
		msg += ". Capacity required = ";
		msg += entry.getCourseComponent().getRoomCapacityRequirement();
		msg += ", capacity given = ";
		msg += entry.getRoom().getCapacity();
		msg += ".";
		
		return msg;
	}
}
