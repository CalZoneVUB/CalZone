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
public class TeacherLecturePreferenceCV implements ConstraintViolation {
	Entry entry;

	public TeacherLecturePreferenceCV(Entry entry) {
		this.entry = entry;
	}

	@Override
	public String description() {
		// TODO Internationalize
//		String msg = "Course ";
//		try {
//		msg += entry.getCourseComponent().getCourse().getCourseName();
//		msg += " has a prefered day of ";
//		msg += DateUtility.getDayOfWeek(entry.getCourseComponent()
//				.getTeacherLecturePreference().getDayOfWeek());
//		msg += ", a prefered starting hour of ";
//		msg += entry.getCourseComponent().getTeacherLecturePreference().getStartingHour();
//		msg += ":00, a prefered ending hour of ";
//		msg += entry.getCourseComponent().getTeacherLecturePreference().getEndingHour();
//		msg += ":00 but the course is given at ";
//		msg += DateUtility.getDayOfWeek(DateUtility.getDayOfWeek(entry.getStartingDate()));
//		msg += " between ";
//		msg += DateUtility.getHourOfDay(entry.getStartingDate());
//		msg += ":00 and ";
//		msg += DateUtility.getHourOfDay(entry.getEndingDate());
//		msg += ":00.";
//		} catch (Exception e) {
//			return "" ;
//		}
//		return msg;
		return "";
	}
}
