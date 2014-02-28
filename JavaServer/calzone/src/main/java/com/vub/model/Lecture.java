/**
 * IGNORE UNTIL 3RD ITERATION
 * 
 * @author youri
 */
package com.vub.model;

import java.util.Date;

public class Lecture {
	Room room;
	Course course;
	Date date;
	
	@Override
	public String toString() {
		return "Lecture [room=" + room + ", course=" + course + ", date="
				+ date + "]";
	}
	
}
