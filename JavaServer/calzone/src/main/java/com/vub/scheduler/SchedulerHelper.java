package com.vub.scheduler;

import java.util.ArrayList;
import java.util.List;

import com.vub.model.Entry;

public class SchedulerHelper {
	/**
	 * Calculates a score of an entry list based and spare hours and a noon
	 * break in the schedule. A spare hour is considered as a noon break if it
	 * is in the period 12-14 hour.
	 * <p>
	 * Following case are distinguished:
	 * <ul>
	 * <li>Case 1: Only class before noon.
	 * <ul>
	 * <li>Subcase 1: No spare hours. <b>Score = 0</b>
	 * <li>Subcase 2: Spare hours. <b>Score = -#spareHours</b>
	 * </ul>
	 * <li>Case 2: Only class after noon.
	 * <ul>
	 * <li>Subcase 1: No spare hours. <b>Score = 0</b>
	 * <li>Subcase 2: Spare hours. <b>Score = -#spareHours</b>
	 * </ul>
	 * <li>Case 3: Class before and after noon.
	 * <ul>
	 * <li>Subcase 1: One spare hour during noon. <b>Score = 0</b>
	 * <li>Subcase 2: More then one spare hour and there is at least one spare
	 * hour during noon. <b>Score = - (#spareHours - 1) </b>
	 * <li>Subcase 3: Zero or more spare hours of which none during noon. <b>- 5
	 * * #spareHours</b>
	 * </ul>
	 * <li>Case 4: First class starts at noon (at exactly 12).
	 * <ul>
	 * <li>Last class ends before noon break. This is correct.
	 * <li>Last class ends after noon break. Same 3 subcases as in case 3.
	 * <li>First class ends after noon break. Not correct. + possibly spare
	 * hours after this.
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
				- firstEntry.getStartDate().getTime();

		hoursOfClass = (long) firstEntry.getCourseComponent().getDuration();

		Entry previousEntry = firstEntry;
		orderedEntryList.remove(0); // since we don't want firstEntry in the
		// coming for-loop
		for (Entry e : orderedEntryList) {
			int endedAt = Entry.calcEndDate(previousEntry).getHours();
			int startingAt = e.getStartDate().getHours();
			if (startingAt - endedAt != 0)
				listOfSpareHours.add(new Pair<Integer, Integer>(endedAt,
						startingAt));
			hoursOfClass += (long) e.getCourseComponent().getDuration();
			previousEntry = e;
		}

		// Since totalDurationOfDay is in milliseconds, we need to convert it to
		// hours
		totalDurationOfDay /= 60 * 60 * 1000;
		spareHours = totalDurationOfDay - hoursOfClass;
		if (Entry.calcEndDate(lastEntry).getHours() <= 12
				|| firstEntry.getStartDate().getHours() >= 14) {
			if (spareHours > 0)
				return -spareHours;
			else
				return 0;
		} else {
			for (Pair<Integer, Integer> s : listOfSpareHours) {
				int middle = (s.first + s.second) / 2;
				if ((middle >= 12) && (middle <= 14)) {
					spareHourAtNoon = true;
					break;
				}
			}

			if (spareHourAtNoon) {
				if (spareHours == 1) {
					return 0;
				} else
					return -(spareHours - 1);
			} else
				return -5 * spareHours;
		}

	}
}
