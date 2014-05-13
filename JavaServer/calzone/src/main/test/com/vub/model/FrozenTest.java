package com.vub.model;

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
	 * 
	 */
	@Test
	public void freezeEntryTest(){
		logEntries("Entry Freeze Test", environment);
	}
	/**
	 * 
	 */
	@Test
	public void freezeCourseTest(){
		
	}
	/**
	 * 
	 */
	@Test
	public void freezeTrajectTest(){
		
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
