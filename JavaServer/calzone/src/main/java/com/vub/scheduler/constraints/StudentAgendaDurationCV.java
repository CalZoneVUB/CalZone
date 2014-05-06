/**
 * 
 */
package com.vub.scheduler.constraints;

import com.vub.model.Traject;

/**
 * @author pieter
 *
 */
public class StudentAgendaDurationCV implements ConstraintViolation {
	Traject traject;
	
	public StudentAgendaDurationCV(Traject traject) {
		this.traject = traject;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

}
