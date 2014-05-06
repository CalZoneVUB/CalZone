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
public class SpareHoursCV implements ConstraintViolation {
	Traject traject;
	Date date;
	
	public SpareHoursCV(Traject traject, Date date) {
		this.traject = traject;
		this.date = date;
	}
	

	@Override
	public String description() {
		// TODO Internationalize
		String msg = "In ";
		msg += traject.getTrajectName();
		msg += " at ";
		msg += DateUtility.formatAsDate(date);
		msg += " there is a violation with the rules of spare hours.";
		
		return msg;
	}

}
