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
public class NoLecturesInTheEveningCV implements ConstraintViolation {
	Entry entry;
	
	public NoLecturesInTheEveningCV(Entry entry) {
		this.entry = entry;
	}
	
	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += DateUtility.formatAsDateTime(entry.getStartingDate());
		msg += " ends after 19:00.";
		
		return msg;
	}

}
