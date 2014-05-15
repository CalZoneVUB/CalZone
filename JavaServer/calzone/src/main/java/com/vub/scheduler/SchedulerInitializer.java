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
	
	public static List<Date> createSlotsOfYear(int academicYearStart) {
		List<Date> result = createSlotsOfTerm(academicYearStart, 1);
		result.addAll(createSlotsOfTerm(academicYearStart, 2));
		
		return result;
	}

	/**
	 * 
	 * @param academicYearStart
	 *            The year in which the academic year starts. If the term is 2,
	 *            this year will automatically be incremented.
	 * @param term
	 *            can only be 1 or 2
	 * @return
	 */
	public static List<Date> createSlotsOfTerm(int academicYearStart, int term) {
		int year;
		List<Integer> weekNumbers = new ArrayList<Integer>();

		if (term == 1) {
			year = academicYearStart;

			// Calculate week numbers
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, 8); // Set month to september
			c.set(Calendar.WEEK_OF_MONTH, 2);
			int startWeekNumber = c.get(Calendar.WEEK_OF_YEAR);

			for (int i = 0; i < 12; i++) {
				weekNumbers.add(startWeekNumber++);
			}
		} else { // term == 2
			year = academicYearStart + 1;

			// Calculate week numbers
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, 1); // Set month to february
			c.set(Calendar.WEEK_OF_MONTH, 2);
			int startWeekNumber = c.get(Calendar.WEEK_OF_YEAR);

			for (int i = 0; i < 12; i++) {
				weekNumbers.add(startWeekNumber++);
			}
		}

		return createSlotsOfTerm(year, weekNumbers);
	}

	/**
	 * Create an array of all lecture slots during the specified term. See also
	 * {@link #createSlotsOfWeek(int, int) createSlotsOfWeek} for more
	 * information about the created slots.
	 * 
	 * @param year
	 * @param weekNumbers
	 *            An array of weeknumbers of the year for which we need to
	 *            create slots. These are based on the year (not the academic
	 *            year).
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
	 * <li>Lectures are given from monday till friday.</li>
	 * <li>Courses can start at 8, 10, 13 and 15 hour.</li>
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

		for (int dayNumber = Calendar.MONDAY; dayNumber <= Calendar.FRIDAY; dayNumber++) {
			cal.set(Calendar.DAY_OF_WEEK, dayNumber);
			for (int hourOfDay : Arrays.asList(8, 10, 13, 15)) {
				cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				list.add(cal.getTime());
			}
		}
		return list;
	}
}
