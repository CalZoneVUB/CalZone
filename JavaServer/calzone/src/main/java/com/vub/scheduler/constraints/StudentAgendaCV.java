/**
 * 
 */
package com.vub.scheduler.constraints;

import com.vub.model.Entry;
import com.vub.model.Traject;

/**
 * @author Pieter Meiresone
 *
 */
public class StudentAgendaCV implements ConstraintViolation {
	Traject traject;
	Entry entry1;
	Entry entry2;
	
	public StudentAgendaCV(Traject traject, Entry entry1, Entry entry2) {
		this.traject = traject;
		this.entry1 = entry2;
		this.entry2 = entry2;
	}

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry1.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += entry1.getStartingDate().toString();
		msg += " conflicts with the course ";
		msg += entry2.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += entry2.getStartingDate().toString();
		msg += ".";
		return msg;
	}

}
