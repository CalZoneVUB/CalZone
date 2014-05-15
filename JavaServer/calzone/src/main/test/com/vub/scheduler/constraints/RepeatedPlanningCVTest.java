/**
 * 
 */
package com.vub.scheduler.constraints;

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
import com.vub.utility.DateUtility;

/**
 * @author pieter
 *
 */
public class RepeatedPlanningCVTest extends ConstraintViolationTest {
	public RepeatedPlanningCVTest() {
		super(RuleNames.repeatedPlanningViolated);
	}
	
	@Test
	public void testRuleFired() {
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(DateUtility.createDate(2014, 7, 18, 10, 0)); // Day is monday
		startDateList.add(DateUtility.createDate(2014, 7, 26, 13, 0)); // Day is tuesday
		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom());
		
		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> ccList = new ArrayList<CourseComponent>();
		
		CourseComponent cc = Helper.createCourseComponent(teachers, 20, 4, 2,
				CourseComponentType.HOC);
		cc.setEndingDate(DateUtility.createDate(2014, 9, 1));
		ccList.add(cc);

		// Traject list
		Set<Traject> trajectSet = Helper.createTraject(ccList);

		// Entry list
		List<Entry> entryList = SchedulerSolver.createEntryList(startDateList,
				roomList, trajectSet);
		entryList.get(0).setStartingDate(startDateList.get(0));
		entryList.get(1).setStartingDate(startDateList.get(1));
		logEntries(this.ruleName, entryList);

		// Initialize solution
		Scheduler solution = new Scheduler(startDateList, roomList, entryList,
				trajectSet);
		SchedulerScoreCalculator ssc = new SchedulerScoreCalculator(solution);

		Collection<String> constraintNames = getViolatedConstraintNames(ssc
				.getScoreDirector());

		assertTrue("No " + this.ruleName + " detected.",
				constraintNames
						.contains(this.ruleName));
		assertConstraintViolationObject(ssc);
	}
	
	@Test
	public void testRuleNotFired() {
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(DateUtility.createDate(2014, 7, 18, 10, 0)); // Day is monday
		startDateList.add(DateUtility.createDate(2014, 7, 25, 10, 0)); // Day is tuesday
		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom());
		
		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> ccList = new ArrayList<CourseComponent>();
		
		CourseComponent cc = Helper.createCourseComponent(teachers, 20, 4, 2,
				CourseComponentType.HOC);
		cc.setEndingDate(DateUtility.createDate(2014, 9, 1));
		ccList.add(cc);

		// Traject list
		Set<Traject> trajectSet = Helper.createTraject(ccList);

		// Entry list
		List<Entry> entryList = SchedulerSolver.createEntryList(startDateList,
				roomList, trajectSet);
		entryList.get(0).setStartingDate(startDateList.get(0));
		entryList.get(1).setStartingDate(startDateList.get(1));
		logEntries(this.ruleName, entryList);

		// Initialize solution
		Scheduler solution = new Scheduler(startDateList, roomList, entryList,
				trajectSet);
		SchedulerScoreCalculator ssc = new SchedulerScoreCalculator(solution);

		Collection<String> constraintNames = getViolatedConstraintNames(ssc
				.getScoreDirector());

		assertFalse("A " + this.ruleName + " is detected.",
				constraintNames
						.contains(this.ruleName));
		assertConstraintViolationObject(ssc);
	}
}
