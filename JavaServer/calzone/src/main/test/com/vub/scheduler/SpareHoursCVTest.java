/**
 * 
 */
package com.vub.scheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.vub.model.CourseComponent;
import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.Traject;
import com.vub.model.User;
import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.utility.DateUtility;

/**
 * @author pieter
 *
 */
public class SpareHoursCVTest extends ConstraintViolationTest {
	/**
	 * Test for Case 1 subcase 2.  Only class before noon and 1 spare hour. Excepted score is 0/-1.
	 */
	@Test
	public void spareHoursViolated() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(DateUtility.createDate(2014, 3, 24, 8, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 11, 0));

		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom());

		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> ccList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 2; ++i) {
			ccList.add(Helper.createCourseComponent(teachers, 20, 2, 2,
					CourseComponentType.HOC));
		}

		// Traject list
		Set<Traject> trajectSet = Helper.createTraject(ccList);

		// Entry list
		List<Entry> entryList = SchedulerSolver.createEntryList(startDateList,
				roomList, trajectSet);
		int index = 0;
		for (Entry e : entryList) {
			e.setStartingDate(startDateList.get(index++));
		}
		logEntries(RuleNames.studentAgendaDurationViolated, entryList);

		// Initialize solution
		Scheduler solution = new Scheduler(startDateList, roomList, entryList,
				trajectSet);
		SchedulerScoreCalculator ssc = new SchedulerScoreCalculator(solution);

		Collection<String> constraintNames = getViolatedConstraintNames(ssc
				.getScoreDirector());
		
		
		assertTrue("No " + RuleNames.spareHoursViolated + " detected.", constraintNames.contains(RuleNames.studentAgendaDurationViolated));
		
		assertEquals("HardScore is not 0.", 0, ssc.getScore().getHardScore());
		assertEquals("SoftScore is not -1", -1, ssc.getScore().getSoftScore());
		
	}
}
