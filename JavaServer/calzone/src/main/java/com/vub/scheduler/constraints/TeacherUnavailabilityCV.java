/**
 * 
 */
package com.vub.scheduler.constraints;

import com.vub.model.Entry;
import com.vub.model.TeacherUnavailability;
import com.vub.utility.DateUtility;

/**
 * @author pieter
 *
 */
public class TeacherUnavailabilityCV implements ConstraintViolation {
	Entry entry;
	TeacherUnavailability teacherUnavailability;
	
	public TeacherUnavailabilityCV(Entry entry, TeacherUnavailability teacherUnavailability) {
		this.entry = entry;
		this.teacherUnavailability = teacherUnavailability;
	}
	
	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Teacher ";
		msg += entry.getTeacher().getUsername();
		msg += "prefers not to teach on ";
		msg += DateUtility.getDayOfWeek(teacherUnavailability.getDayOfWeek() + 1);
		msg += " between ";
		msg += teacherUnavailability.getStartingHour();
		msg += ":00 and ";
		msg += teacherUnavailability.getEndingHour();
		msg += ":00 but the course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += "is given at ";
		msg += DateUtility.getDayOfWeek(DateUtility.getDayOfWeek(entry.getStartingDate()));
		msg += " between ";
		msg += DateUtility.getHourOfDay(entry.getStartingDate());
		msg += ":00 and ";
		msg += DateUtility.getHourOfDay(entry.getEndingDate());
		msg += ":00.";
		
		return msg;
	}

}
