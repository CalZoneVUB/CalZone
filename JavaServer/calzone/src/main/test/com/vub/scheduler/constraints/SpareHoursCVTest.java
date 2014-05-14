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
public class SpareHoursCVTest extends ConstraintViolationTest {
	public SpareHoursCVTest() {
		super(RuleNames.spareHoursViolated);
	}
	
	/**
	 * Test for Case 1 subcase 2. Only class before noon and 1 spare hour.
	 * Excepted score is 0/-1.
	 */
	@Test
	public void spareHoursViolated() {
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
		logEntries(this.ruleName, entryList);

		// Initialize solution
		Scheduler solution = new Scheduler(startDateList, roomList, entryList,
				trajectSet);
		SchedulerScoreCalculator ssc = new SchedulerScoreCalculator(solution);

		Collection<String> constraintNames = getViolatedConstraintNames(ssc
				.getScoreDirector());
		logger.info("List of constraint names: {}", constraintNames);

		assertTrue("No " + this.ruleName + " detected.",
				constraintNames.contains(this.ruleName));
		assertConstraintViolationObject(ssc);
	}
	
	/**
	 * Test for Case 1 subcase 2. Only class before noon and 1 spare hour.
	 * Excepted score is 0/-1.
	 */
	@Test
	public void test9HoursPerDay() {
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

		for (int i = 0; i < 4; ++i) {
			ccList.add(Helper.createCourseComponent(teachers, 20, 2, 2,
					CourseComponentType.HOC));
		}
		ccList.add(Helper.createCourseComponent(teachers, 20, 1, 1,
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
		logEntries(this.ruleName + " 9 hours of class ", entryList);

		// Initialize solution
		Scheduler solution = new Scheduler(startDateList, roomList, entryList,
				trajectSet);
		SchedulerScoreCalculator ssc = new SchedulerScoreCalculator(solution);
		Collection<String> constraintNames = getViolatedConstraintNames(ssc
				.getScoreDirector());
		logger.info("List of constraint names: {}", constraintNames);

		assertTrue("No " + this.ruleName + " detected.",
				constraintNames.contains(this.ruleName));
		assertConstraintViolationObject(ssc);
	}
	
	/**
	 * Test for Case 1 subcase 2. Only class before noon and 1 spare hour.
	 * Excepted score is for a day like this is 0/-1.
	 * 
	 * This case is repeated over 2 days. So total expected score is 0/-2.
	 */
	@Test
	public void testOverMultipleDays() {
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(DateUtility.createDate(2014, 3, 24, 8, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 11, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 8, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 11, 0));

		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom());

		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> ccList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 4; ++i) {
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
		logEntries(this.ruleName + " multiple days", entryList);

		// Initialize solution
		Scheduler solution = new Scheduler(startDateList, roomList, entryList,
				trajectSet);
		SchedulerScoreCalculator ssc = new SchedulerScoreCalculator(solution);
		Collection<String> constraintNames = getViolatedConstraintNames(ssc
				.getScoreDirector());
		logger.info("List of constraint names: {}", constraintNames);

		assertTrue("No " + this.ruleName + " detected.",
				constraintNames.contains(this.ruleName));
		assertConstraintViolationObject(ssc);
	}
}
