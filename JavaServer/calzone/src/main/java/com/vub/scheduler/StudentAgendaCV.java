/**
 * 
 */
package com.vub.scheduler;

import com.vub.model.Entry;

/**
 * @author Pieter Meiresone
 *
 */
public class StudentAgendaCV extends ConstraintViolation {
	Entry entry2;
	
	public StudentAgendaCV(Entry entry1, Entry entry2) {
		super(entry1);
		this.entry2 = entry2;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += entry.getStartingDate().toString();
		msg += " conflicts with the course ";
		msg += entry2.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += entry2.getStartingDate().toString();
		msg += ".";
		return msg;
	}

}
