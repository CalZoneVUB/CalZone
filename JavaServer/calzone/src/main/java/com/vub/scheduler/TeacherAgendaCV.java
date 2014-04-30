package com.vub.scheduler;

import com.vub.model.Entry;

public class TeacherAgendaCV extends ConstraintViolation {
	Entry entry2;
	
	public TeacherAgendaCV(Entry entry, Entry entry2) {
		super(entry);
		this.entry2 = entry2;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg ="Teacher ";
		msg += entry.getCourseComponent().getTeachers().iterator().next();
		msg += "  ";
		msg += " gives course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " at ";
		msg += entry.getStartingDate().toString();
		msg += " and course ";
		msg += entry2.getCourseComponent().getCourse().getCourseName();
		msg += " at ";
		msg += entry2.getStartingDate().toString();
		
		return msg;
	}
	
}
