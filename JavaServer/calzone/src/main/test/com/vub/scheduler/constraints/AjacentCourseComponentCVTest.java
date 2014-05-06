/**
 * 
 */
package com.vub.scheduler.constraints;

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
import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.Traject;
import com.vub.model.User;
import com.vub.scheduler.Helper;
import com.vub.scheduler.Scheduler;
import com.vub.scheduler.SchedulerScoreCalculator;
import com.vub.scheduler.SchedulerSolver;
import com.vub.scheduler.constraints.RuleNames;
import com.vub.utility.DateUtility;

/**
 * Test class for the rule "adjacentCcViolated".
 * 
 * @author Pieter Meiresone
 */
public class AjacentCourseComponentCVTest extends ConstraintViolationTest {
	/**
	 * Test for the rule "adjacentCcViolated". This tests 2 entries of the same
	 * coursecomponent. Tests if the rules fires.
	 */
	@Test
	public void adjacentCcViolated() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(DateUtility.createDate(2014, 3, 24, 8, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 10, 0));

		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom());

		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> ccList = new ArrayList<CourseComponent>();

		ccList.add(Helper.createCourseComponent(teachers, 20, 4, 2,
				CourseComponentType.HOC));

		// Traject list
		Set<Traject> trajectSet = Helper.createTraject(ccList);

		// Entry list
		List<Entry> entryList = SchedulerSolver.createEntryList(startDateList,
				roomList, trajectSet);
		int index = 0;
		for (Entry e : entryList) {
			e.setStartingDate(startDateList.get(index++));
		}
		logEntries(RuleNames.adjacentCcViolated, entryList);

		// Initialize solution
		Scheduler solution = new Scheduler(startDateList, roomList, entryList,
				trajectSet);
		SchedulerScoreCalculator ssc = new SchedulerScoreCalculator(solution);

		Collection<String> constraintNames = getViolatedConstraintNames(ssc
				.getScoreDirector());

		assertTrue("No " + RuleNames.adjacentCcViolated + " detected.",
				constraintNames
						.contains(RuleNames.adjacentCcViolated));

		assertEquals("HardScore is not 0.", 0, ssc.getScore().getHardScore());
		assertEquals("SoftScore is not -1", -1, ssc.getScore().getSoftScore());
	}
}
