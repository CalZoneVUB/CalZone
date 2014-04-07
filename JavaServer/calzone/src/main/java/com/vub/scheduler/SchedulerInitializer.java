package com.vub.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class that contains static methods for initializing data for the scheduler.
 * Fetches the data from the database.
 * 
 * @author pieter
 * 
 */
public class SchedulerInitializer {

	/**
	 * Create an array of all lecture slots during the specified term. See also
	 * {@link #createSlotsOfWeek(int, int) createSlotsOfWeek} for more
	 * information about the created slots.
	 * 
	 * @param year
	 * @param weekNumbers
	 *            An array of weeknumbers of the year for which we need to
	 *            create slots.
	 * @return an array of all lecture slots during the specified term.
	 */
	public static List<Date> createSlotsOfTerm(int year,
			List<Integer> weekNumbers) {
		List<Date> list = new ArrayList<Date>();

		for (Integer weekNumber : weekNumbers) {
			list.addAll(createSlotsOfWeek(year, weekNumber));
		}

		return list;
	}

	/**
	 * Create an array of all lecture slots during one week. Following rules are
	 * used:
	 * <ul>
	 * <li>Lectures are given from monday till saterday.</li>
	 * <li>Courses can start at 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 or 18 hour.
	 * </li>
	 * </ul>
	 * 
	 * @param year
	 * @param weekNumber
	 *            The weeknumber of the year for which the dates need to be
	 *            created.
	 * @return an array of all lecture slots during one week
	 */
	public static List<Date> createSlotsOfWeek(int year, int weekNumber) {
		List<Date> list = new ArrayList<Date>();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNumber);

		for (int dayNumber = Calendar.MONDAY; dayNumber <= Calendar.SATURDAY; dayNumber++) {
			cal.set(Calendar.DAY_OF_WEEK, dayNumber);
			for (int hourOfDay : Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15,
					16, 17, 18)) {
				cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
				list.add(cal.getTime());
			}
		}
		return list;
	}
}
