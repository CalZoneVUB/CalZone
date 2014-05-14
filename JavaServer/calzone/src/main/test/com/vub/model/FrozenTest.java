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
 * The reasoning is analog for trajects.
 *
 * This class will test whether the freezing acts as expected to behave.
 *
 * @author youri
 *
 */
public class FrozenTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private List<Entry> environmentToFreeze;
	private List<Entry> environmentToUnfreeze;
	private final boolean frozen = true;
	private final boolean unfrozen = false;
	/**
	 * Method for logging all the entries. This is used for debugging.
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
	 * This method sets a list of {@link Entry entries} that will be used in the freeze tests. 
	 * The entries come from a traject containing 2 courses with each a course component.
	 */
	@Before
	public void getTestEnvironmentToFreeze(){
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
			e.getCourseComponent().getEntries().add(e);
		}
		this.environmentToFreeze = entryList;	
	}

	/**
	 * 
	 */
	@Before
	public void getTestEnvironmentToUnfreeze(){
		List<Entry> env = new ArrayList<Entry>(environmentToFreeze);
		Traject toFreeze = env.get(0).getCourseComponent().getCourse().getTrajects().iterator().next();
		toFreeze.setFrozen(frozen);
		environmentToUnfreeze = env;
	}
	/**
	 * This first test freezes exactly one Entry and checks if this is saved.
	 */
	@Test
	public void freezeEntryTest(){
		logEntries("Entry Freeze Test", environmentToFreeze);
		Entry toFreeze = environmentToFreeze.get(0);
		toFreeze.setFrozen(frozen);
		logEntries("Entry Freeze Test RESULT", environmentToFreeze);
		assertTrue("Entry isn't set frozen.",toFreeze.isFrozen());	
	}

	/**
	 * This second test freezes a course of the environment
	 * This test succeeds when all the entries belonging to this course (via its course components)
	 * are set frozen.
	 */
	@Test
	public void freezeCourseTest(){
		logEntries("Course Freeze Test Environment:", environmentToFreeze);
		Course toFreeze = environmentToFreeze.get(0).getCourseComponent().getCourse();
		toFreeze.setFrozen(frozen);
		logEntries("Course Freeze Test RESULT", environmentToFreeze);
		assertTrue("Course isn't set frozen.",toFreeze.isFrozen());
		for(CourseComponent cc: toFreeze.getCourseComponents()){
			for(Entry e: cc.getEntries()){
				assertTrue("An entry of the course isn't set frozen.", e.isFrozen());
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void freezeTrajectTest(){
		logEntries("Traject Freeze Test Environment:", environmentToFreeze);
		Traject toFreeze = environmentToFreeze.get(0).getCourseComponent().getCourse().getTrajects().iterator().next();
		toFreeze.setFrozen(frozen);
		logEntries("Traject Freeze Test RESULT", environmentToFreeze);
		assertTrue("Traject isn't (completely) frozen.",toFreeze.isFrozen());
		for(Course c : toFreeze.getCourses()){
			assertTrue("A course isn't set frozen.",toFreeze.isFrozen());
			for(CourseComponent cc: c.getCourseComponents()){
				for(Entry e: cc.getEntries()){
					assertTrue("An entry of a course isn't set frozen.", e.isFrozen());
				}
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void unfreezeEntryTest(){
		logEntries("Entry Unfreeze Test", environmentToUnfreeze);
		Entry toFreeze = environmentToFreeze.get(0);
		toFreeze.setFrozen(unfrozen);
		logEntries("Entry Unfreeze Test RESULT:", environmentToUnfreeze);
		assertFalse("Entry is still set frozen",toFreeze.isFrozen());
	}

	/**
	 * 
	 */
	@Test
	public void unfreezeCourseTest(){
		logEntries("Course Unfreeze Test", environmentToUnfreeze);
		Course toFreeze = environmentToFreeze.get(0).getCourseComponent().getCourse();
		toFreeze.setFrozen(unfrozen);
		logEntries("Course Unfreeze Test RESULT:", environmentToUnfreeze);
		assertFalse("Course is still set frozen",toFreeze.isFrozen());
		for(CourseComponent cc: toFreeze.getCourseComponents()){
			for(Entry e: cc.getEntries()){
				assertFalse("An entry of the course is still set frozen.", e.isFrozen());
			}
		}
		assertFalse("Traject is still set frozen.",toFreeze.getTrajects().iterator().next().isFrozen());
	}

	/**
	 * 
	 */
	@Test
	public void unfreezeTrajectTest(){
		logEntries("Traject Unfreeze Test", environmentToUnfreeze);
		Traject toFreeze = environmentToFreeze.get(0).getCourseComponent().getCourse().getTrajects().iterator().next();
		toFreeze.setFrozen(unfrozen);
		logEntries("Traject Unfreeze Test RESULT:", environmentToUnfreeze);
		assertFalse("Traject is still set frozen",toFreeze.isFrozen());
		for(Course c : toFreeze.getCourses()){
			assertFalse("A course is still set frozen.",toFreeze.isFrozen());
			for(CourseComponent cc: c.getCourseComponents()){
				for(Entry e: cc.getEntries()){
					assertFalse("An entry of a course is still set frozen.", e.isFrozen());
				}
			}
		}
	}	
}
