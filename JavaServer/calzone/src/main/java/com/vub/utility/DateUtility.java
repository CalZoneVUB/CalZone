/**
 * 
 */
package com.vub.utility;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Pieter Meiresone
 *
 */
public class DateUtility {
	/**
	 * 
	 * @param year
	 * @param month IMPORTANT: zero based!
	 * @param day IMPORT: one based!
	 * @return
	 */
	public static Date createDate(int year, int month, int day) {
		return createDate(year, month, day, 0,0);
	}
	
	/**
	 * 
	 * @param year
	 * @param month IMPORTANT: zero based!
	 * @param day IMPORTANT: one based! 
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date createDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		
		return cal.getTime();
	}
}
