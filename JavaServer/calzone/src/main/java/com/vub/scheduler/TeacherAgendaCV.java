package com.vub.scheduler;

import com.vub.model.Entry;

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
		msg += entry1.getStartingDate().toString();
		msg += " and course ";
		msg += entry2.getCourseComponent().getCourse().getCourseName();
		msg += " at ";
		msg += entry2.getStartingDate().toString();
		
		return msg;
	}
	
}
