/**
 * 
 */
package com.vub.scheduler.constraints;

import com.vub.model.Entry;

/**
 * @author pieter
 *
 */
public class RoomAgendaCV implements ConstraintViolation {
	Entry entry1;
	Entry entry2;
	
	public RoomAgendaCV(Entry entry1, Entry entry2) {
		this.entry1 = entry1;
		this.entry2 = entry2;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

}
