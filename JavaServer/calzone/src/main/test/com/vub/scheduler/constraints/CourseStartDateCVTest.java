/**
 * 
 */
package com.vub.scheduler.constraints;

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
 * Test class for the rule "courseStartDateViolated"
 * 
 * @author pieter
 *
 */
public class CourseStartDateCVTest extends ConstraintViolationTest {
	public CourseStartDateCVTest() {
		super(RuleNames.courseStartDateViolated);
	}
	
	public SchedulerScoreCalculator execute() {
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(DateUtility.createDate(2014, 3, 24, 8, 0));

		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom());

		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> ccList = new ArrayList<CourseComponent>();
		
		CourseComponent cc = Helper.createCourseComponent(teachers, 20, 4, 2,
				CourseComponentType.HOC);
		cc.setStartingDate(DateUtility.createDate(2014, 6, 1));
		ccList.add(cc);

		// Traject list
		Set<Traject> trajectSet = Helper.createTraject(ccList);

		// Entry list
		List<Entry> entryList = SchedulerSolver.createEntryList(startDateList,
				roomList, trajectSet);
		entryList.get(0).setStartingDate(startDateList.get(0));
		logEntries(this.ruleName, entryList);

		// Initialize solution
		Scheduler solution = new Scheduler(startDateList, roomList, entryList,
				trajectSet);
		return new SchedulerScoreCalculator(solution);
	}
	
	@Test
	public void simple() {
		SchedulerScoreCalculator ssc = execute();
		
		Collection<String> constraintNames = getViolatedConstraintNames(ssc
				.getScoreDirector());

		assertTrue("No " + this.ruleName + " detected.",
				constraintNames
						.contains(this.ruleName));
		
		assertConstraintViolationObject(ssc);
	}
}
