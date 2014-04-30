/**
 * 
 */
package com.vub.scheduler;

import com.vub.model.Entry;

/**
 * @author Pieter Meiresone
 *
 */
public abstract class ConstraintViolation {
	Entry entry;
	
	public ConstraintViolation(Entry entry) {
		this.entry = entry;
	}
	
	/**
	 * @return a description of the violated constraint. This message is internationalized.
	 */
	public abstract String description();
}
