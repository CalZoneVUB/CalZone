package com.vub.scheduler.constraints;

import com.vub.model.Entry;
import com.vub.utility.DateUtility;

public class RoomCapacityCV implements ConstraintViolation {
	Entry entry;
	
	public RoomCapacityCV(Entry entry) {
		this.entry = entry;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Room capacity violation for course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " at ";
		msg += DateUtility.formatAsDateTime(entry.getStartingDate());
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
