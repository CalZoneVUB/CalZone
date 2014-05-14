package com.vub.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.scheduler.Helper;
import com.vub.scheduler.SchedulerSolver;
import com.vub.utility.DateUtility;

/**
 * This is a test class to test the freezing system.
 * Users can freeze Entry's, Courses and Trajects.
 * When one the above is (un)frozen, it will affect the status of the other two.
 *
 * An Entry is frozen when its data member is set true.
 * A course must be set frozen when all of its entries are set frozen and vice versa: when a course is set frozen all of its entries should be set frozen.
 * 
 * The reasoning is analogue for trajects.
 *
 * This class will test whether the freezing acts as expected to behave.
 *
 * @author youri
 *
 */
public class FrozenTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private List<Entry> environment;
	/**
	 * Method for logging the all the entries. This is used for debugging.
	 * 
	 * @param description
	 *            '"Unit Test: " + description' will be send to the logger.
	 * @param entryList
	 *            The entries to log to the logger.
	 * 
	 * @author pieter
	 */
	protected void logEntries(String description, List<Entry> entryList) {
		Collections.sort(entryList);
		logger.info("Unit test: " + description);
		for (Entry e : entryList) {
			logger.info(e.toString());
		}
	}
	
	/**
	 * This method returns a list of {@link Entry entries}. 
	 * This list will be used as common test environment for this class.
	 * The entries come from a traject containing 2 courses with each a course component.
	 * @return
	 * The generated entry list.
	 */
	@Before
	public void getTestEnvironment(){
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(DateUtility.createDate(2014, 3, 24, 8, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 11, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 13, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 15, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 17, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 24, 19, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 8, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 11, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 13, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 15, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 17, 0));
		startDateList.add(DateUtility.createDate(2014, 3, 25, 19, 0));
		
		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom());

		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> ccList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 2; ++i) {
			ccList.add(Helper.createCourseComponent(teachers, 20, 10, 2,
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
		this.environment = entryList;	
	}
	
	/**
	 * This first test freezes exactly one Entry and checks if this is saved.
	 */
	@Test
	public void freezeEntryTest(){
		logEntries("Entry Freeze Test", environment);
		Entry toFreeze = environment.get(0);
		toFreeze.setFrozen(true);
		logEntries("Entry Freeze Test RESULT", environment);
		assertTrue("Entry isn't frozen",toFreeze.isFrozen());	
	}
	
	/**
	 * 
	 */
	@Test
	public void freezeCourseTest(){
		logEntries("Course Freeze Test Environment:", environment);
		Course toFreeze = environment.get(0).getCourseComponent().getCourse();
		toFreeze.setFrozen(true);
		logEntries("Course Freeze Test RESULT", environment);
		assertTrue("Course isn't (completely) frozen",toFreeze.isFrozen());
	}
	
	/**
	 * 
	 */
	@Test
	public void freezeTrajectTest(){
		logEntries("Traject Freeze Test Environment:", environment);
		Traject toFreeze = environment.get(0).getCourseComponent().getCourse().getTrajects().iterator().next();
		toFreeze.setFrozen(true);
		logEntries("Traject Freeze Test RESULT", environment);
		assertTrue("Traject isn't (completely) frozen",toFreeze.isFrozen());
	}
	
	/**
	 * 
	 */
	@Test
	public void unfreezeEntryTest(){
		
	}
	
	/**
	 * 
	 */
	@Test
	public void unfreezeCourseTest(){
		
	}
	
	/**
	 * 
	 */
	@Test
	public void unfreezeTrajectTest(){
		
	}	
}
