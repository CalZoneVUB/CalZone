package com.vub.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vub.model.Entry;

public class SchedulerHelper {
	final static Logger logger = LoggerFactory.getLogger(SchedulerHelper.class);
	
	/**
	 * Calculates a score of an entry list based and spare hours and a noon
	 * break in the schedule. A spare hour is considered as a noon break if it
	 * is in the period 12-14 hour.
	 * <p>
	 * Following case are distinguished:
	 * <ul>
	 * <li>Case 1: Only class before the end of noon (= 14 am).
	 * <ul>
	 * <li>Subcase 1: No spare hours. <b>Score = 0</b>
	 * <li>Subcase 2: Spare hours. <b>Score = -#spareHours</b>
	 * </ul>
	 * <li>Case 2: Only class after the noon (= 13 am).
	 * <ul>
	 * <li>Subcase 1: No spare hours. <b>Score = 0</b>
	 * <li>Subcase 2: Spare hours. <b>Score = -#spareHours</b>
	 * </ul>
	 * <li>Case 3: Class before(= 12 am) and after (=14am) noon.
	 * <ul>
	 * <li>Subcase 1: One spare hour during noon. <b>Score = 0</b>
	 * <li>Subcase 2: More then one spare hour and there is at least one spare
	 * hour during noon. <b>Score = - (#spareHours - 1) </b>
	 * <li>Subcase 3: Zero or more spare hours of which none during noon. <b>- 5
	 * * (#spareHours + 1)</b>
	 * </ul>
	 * <li>Case 4: First class starts at noon (at exactly 12).
	 * <ul>
	 * <li>Subcase 1: Last class ends before or at the end of the noon break.
	 * This is correct. <b>Score = 0</b>
	 * <li>Subcase 2: Same 3 subcases as in case 3.<b>Scores : see case 3</b>
	 * </ul>
	 * </ul>
	 * </p>
	 * 
	 * @param orderedEntryList
	 *            The list of (sorted) entries. These entries have to belong to
	 *            the same traject.
	 * @return the calculated score. See description for the score values.
	 */
	public static long checkSpareHoursAndNoonBreak(List<Entry> orderedEntryList) {
		long totalDurationOfDay;
		long hoursOfClass;
		List<Pair<Integer, Integer>> listOfSpareHours;
		boolean spareHourAtNoon = false;
		long spareHours;

		listOfSpareHours = new ArrayList<Pair<Integer, Integer>>();
		Entry lastEntry = orderedEntryList.get(orderedEntryList.size() - 1);
		Entry firstEntry = orderedEntryList.get(0);
		totalDurationOfDay = Entry.calcEndDate(lastEntry).getTime()
				- firstEntry.getStartingDate().getTime();

		hoursOfClass = firstEntry.getCourseComponent().getDuration();

		Entry previousEntry = firstEntry;
		orderedEntryList.remove(0); // since we don't want firstEntry in the
		// coming for-loop
		for (Entry e : orderedEntryList) {
			int endedAt = Entry.calcEndDate(previousEntry).getHours();
			int startingAt = e.getStartingDate().getHours();
			if (startingAt - endedAt != 0)
				listOfSpareHours.add(new Pair<Integer, Integer>(endedAt,
						startingAt));
			hoursOfClass += e.getCourseComponent().getDuration();
			previousEntry = e;
		}

		// Since totalDurationOfDay is in milliseconds, we need to convert it to
		// hours
		totalDurationOfDay /= 60 * 60 * 1000;
		spareHours = totalDurationOfDay - hoursOfClass;
		if (Entry.calcEndDate(lastEntry).getHours() <= 14
				|| firstEntry.getStartingDate().getHours() >= 13) { // Case 1 and 2
			if (spareHours > 0)
				return -spareHours;
			else
				return 0;
		} else {
			for (Pair<Integer, Integer> s : listOfSpareHours) {
				double middle = (s.first + s.second) / 2.0;
				if ((middle >= 12) && (middle <= 14)) {
					spareHourAtNoon = true;
					break;
				}
			}

			if (firstEntry.getStartingDate().getHours() >= 12
					&& Entry.calcEndDate(lastEntry).getHours() <= 14) { 
				// Case 4: Subcase 1
				return 0;
			} else { // Case 3 (+ subcases) + Case 4: Subcase 2

				if (spareHourAtNoon) {
					if (spareHours == 1) {
						return 0;
					} else
						return -(spareHours - 1);
				} else
					return -5 * (spareHours + 1);
			}
		}

	}
}
