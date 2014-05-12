/**
 * 
 */
package com.vub.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Pieter Meiresone
 * 
 */
public class DateUtility {
	/**
	 * The used format for date time through the entire program. The used
	 * format: dd/MM/yyyy HH:mm:ss
	 */
	final static private DateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");
	/**
	 * The used format for date through the entire program. The used format:
	 * dd/MM/yyyy
	 */
	final static private DateFormat formatDate = new SimpleDateFormat(
			"dd/MM/yyyy");

	/**
	 * Formats a date as a string in the form "dd/MM/yyyy HH:mm:ss"
	 * 
	 * @param date the date to format
	 * @return the string representation of the date
	 */
	public static String formatAsDateTime(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * Formats a date as a string in the form "dd/MM/yyyy"
	 * 
	 * @param date the date to format
	 * @return the string representation of the date
	 */
	public static String formatAsDate(Date date) {
		return formatDate.format(date);
	}

	/**
	 * 
	 * @param year
	 * @param month
	 *            IMPORTANT: zero based!
	 * @param day
	 *            IMPORT: one based!
	 * @return a created Date object
	 */
	public static Date createDate(int year, int month, int day) {
		return createDate(year, month, day, 0, 0);
	}

	/**
	 * 
	 * @param year
	 * @param month
	 *            IMPORTANT: zero based!
	 * @param day
	 *            IMPORTANT: one based!
	 * @param hour
	 * @param minute
	 * @return a created Date object
	 */
	public static Date createDate(int year, int month, int day, int hour,
			int minute) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
