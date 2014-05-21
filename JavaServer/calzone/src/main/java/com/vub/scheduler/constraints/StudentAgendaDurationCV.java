/**
 * 
 */
package com.vub.scheduler.constraints;

import java.util.Date;

import com.vub.model.Traject;
import com.vub.utility.DateUtility;

/**
 * @author pieter
 *
 */
public class StudentAgendaDurationCV implements ConstraintViolation {
	Traject traject;
	Date date;
	
	public StudentAgendaDurationCV(Traject traject, Date date) {
		this.traject = traject;
		this.date = date;
	}

	@Override
	public String description() {
//		// TODO Internationalize
//		String msg = "In ";
//		msg += traject.getTrajectName();
//		msg += " at ";
//		msg += DateUtility.formatAsDate(date);
//		msg += " there is more than 9 hours of class.";
//		
//		return msg;
		return "";
	}

}
