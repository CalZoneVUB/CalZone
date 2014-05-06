/**
 * 
 */
package com.vub.scheduler.constraints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import com.vub.scheduler.Helper;
import com.vub.scheduler.Scheduler;
import com.vub.scheduler.SchedulerScoreCalculator;
import com.vub.scheduler.SchedulerSolver;
import com.vub.scheduler.constraints.RuleNames;
import com.vub.utility.DateUtility;

/**
 * @author pieter
 * 
 */
public class StudentAgendaDurationCVTest extends ConstraintViolationTest {
	public StudentAgendaDurationCVTest() {
		super(RuleNames.studentAgendaDurationViolated);
	}

	/**
	 * Test method for the rule "studentAgendaDurationViolated".
	 * 
	 * <p>
	 * Test method: given a certain Schedular-solution, predict the score to be
	 * calculated and assert on the actual calculated score.
	 * 
	 * This test is only executed once, since no random factors are available.
	 * </p>
	 * 
	 * @author Pieter Meiresone
	 */
	@Test
	public void studentAgendaDurationViolated() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(DateUtility.createDate(2014, 3, 24, 8, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 10, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 13, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 15, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 17, 0));

		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom());

		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> ccList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 5; ++i) {
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
		logEntries(this.ruleName, entryList);

		// Initialize solution
		Scheduler solution = new Scheduler(startDateList, roomList, entryList,
				trajectSet);
		SchedulerScoreCalculator ssc = new SchedulerScoreCalculator(solution);

		Collection<String> constraintNames = getViolatedConstraintNames(ssc
				.getScoreDirector());

		assertEquals("HardScore is not 0.", 0, ssc.getScore().getHardScore());
		assertEquals("SoftScore is not -1", -1, ssc.getScore().getSoftScore());
		assertTrue("No " + this.ruleName + " detected.",
				constraintNames.contains(this.ruleName));
	}

	/**
	 * Test method for the rule "studentAgendaDurationViolated".
	 * 
	 * <p>
	 * Test method: given a certain Schedular-solution, predict the score to be
	 * calculated and assert on the actual calculated score.
	 * 
	 * This test is only executed once, since no random factors are available.
	 * </p>
	 * 
	 * @author Pieter Meiresone
	 */
	@Test
	public void studentAgendaDurationViolatedBis() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(DateUtility.createDate(2014, 3, 24, 8, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 10, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 13, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 15, 0));

		startDateList.add(DateUtility.createDate(2014, 3, 25, 8, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 10, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 13, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 15, 0));
		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom());

		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> ccList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 8; ++i) {
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
		logEntries(this.ruleName, entryList);

		// Initialize solution
		Scheduler solution = new Scheduler(startDateList, roomList, entryList,
				trajectSet);
		SchedulerScoreCalculator ssc = new SchedulerScoreCalculator(solution);

		Collection<String> constraintNames = getViolatedConstraintNames(ssc
				.getScoreDirector());
		assertFalse("A " + this.ruleName
				+ " is detected.",
				constraintNames
						.contains(this.ruleName));

		assertEquals("HardScore is not 0.", 0, ssc.getScore().getHardScore());
		assertEquals("SoftScore is not 0", 0, ssc.getScore().getSoftScore());
	}
}
