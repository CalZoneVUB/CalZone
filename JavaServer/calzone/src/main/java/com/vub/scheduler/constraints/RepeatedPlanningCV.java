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
public class RepeatedPlanningCV implements ConstraintViolation {
	Entry entry1;
	Entry entry2;
	
	public RepeatedPlanningCV(Entry entry1, Entry entry2) {
		this.entry1 = entry1;
		this.entry2 = entry2;
	}
	
	@Override
	public String description() {
		// TODO Internationalize
		String msg = "Course ";
		msg += entry1.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += DateUtility.formatAsDateTime(entry1.getStartingDate());
		msg += " and course ";
		msg += entry2.getCourseComponent().getCourse().getCourseName();
		msg += " given at ";
		msg += DateUtility.formatAsDateTime(entry2.getStartingDate());
		msg += " are given in different weeks but not at the same time.";
		
		return msg;
	}

}
