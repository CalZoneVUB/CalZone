/**
 * 
 */
package com.vub.scheduler.constraints;

import com.vub.model.Entry;
import com.vub.utility.DateUtility;

/**
 * @author pieter
 *
 */
public class RoomAgendaCV implements ConstraintViolation {
	Entry entry1;
	Entry entry2;
	
	public RoomAgendaCV(Entry entry1, Entry entry2) {
		this.entry1 = entry1;
		this.entry2 = entry2;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Room ";
		msg += entry1.getRoom().toString();
		msg += " is double booked for courses ";
		msg += entry1.getCourseComponent().getCourse().getCourseName();
		msg += " and ";
		msg += entry2.getCourseComponent().getCourse().getCourseName();
		msg += " at ";
		if (entry1.getStartingDate().compareTo(entry2.getStartingDate()) < 0) {
			msg += DateUtility.formatAsDateTime(entry1.getStartingDate());
		} else {
			msg += DateUtility.formatAsDateTime(entry2.getStartingDate());
		}
		msg += ".";
		
		return msg;
	}

}
