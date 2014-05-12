package com.vub.scheduler.constraints;

import com.vub.model.Entry;
import com.vub.utility.DateUtility;

public class TeacherAgendaCV implements ConstraintViolation {
	Entry entry1;
	Entry entry2;
	
	public TeacherAgendaCV(Entry entry1, Entry entry2) {
		this.entry1 = entry1;
		this.entry2 = entry2;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg ="Teacher ";
		msg += entry1.getCourseComponent().getTeachers().iterator().next();
		msg += "  ";
		msg += " gives course ";
		msg += entry1.getCourseComponent().getCourse().getCourseName();
		msg += " at ";
		msg += DateUtility.formatAsDateTime(entry1.getStartingDate());
		msg += " and course ";
		msg += entry2.getCourseComponent().getCourse().getCourseName();
		msg += " at ";
		msg += DateUtility.formatAsDateTime(entry2.getStartingDate());
		
		return msg;
	}
	
}
