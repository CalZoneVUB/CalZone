/**
 * 
 */
package com.vub.scheduler.constraints;

import java.util.Date;

import com.vub.model.Traject;

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
		// TODO Auto-generated method stub
		return null;
	}

}
