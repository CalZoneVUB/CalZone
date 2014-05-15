package com.vub.scheduler.constraints;

import org.springframework.beans.factory.annotation.Autowired;

import com.vub.model.Entry;
import com.vub.service.EntryService;
import com.vub.utility.DateUtility;

public class RoomTypeCV implements ConstraintViolation {
	Entry entry;

	@Autowired
	EntryService entryService;
	
	public RoomTypeCV(Entry entry) {
		this.entry = entry;
	}

	@Override
	public String description() {
		// TODO Internationalize
		try {
		System.out.println(entry.toString());
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += DateUtility.formatAsDateTime(entry.getStartingDate());
		msg += " requires a ";
		msg += entry.getCourseComponent().getRoomTypeRequirement().toString();
		msg += " while room ";
		msg += entry.getRoom().getVubNotation();
		msg += " is a ";
		msg += entry.getRoom().getType().toString();
		msg += ".";
		
		return msg;
		} catch (Exception e) {
			return "";
		}
	}
}
