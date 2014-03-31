package com.vub.scheduler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.vub.model.Course;
import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.Room.RoomType;

/**
 * Unit test class for the schedular.
 * 
 * @author pieter
 *
 */
public class SchedularTest {
	/**
	 * Simple test method for the schedular.
	 * 
	 * Case: 4 courses need to be scheduled in 4 date slots.
	 * 
	 * Test passes if all the courses have been assigned a date slot with no overlap.
	 * 
	 */
	@Test
	public void simpleScheduling() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(new Date(2014, 3, 24, 8, 0, 0));
		startDateList.add(new Date(2014, 3, 24, 10, 0, 0));
		startDateList.add(new Date(2014, 3, 24, 13, 0, 0));
		startDateList.add(new Date(2014, 3, 24, 15, 0, 0));
		
		// RoomList
		Room room = new Room();
		room.setCapacity(30);
		room.setProjectorEquipped(false);
		room.setType(RoomType.ClassRoom);
		List<Room> roomList = new ArrayList<Room>();
		roomList.add(room);
		roomList.add(room);
		roomList.add(room);
		roomList.add(room);
		
		// Couse list
		List<Course> courseList = new ArrayList<Course>();
		Course course = new Course();
		courseList.add(course);
		courseList.add(course);
		courseList.add(course);
		courseList.add(course);
		SchedularSolver solver = new SchedularSolver(startDateList, roomList, courseList);
		Schedular solution = solver.run();
		
		
		/*
		 * Verify solution: check if there are is no overlap on the startdate of the
		 * courses.
		 */
		List<Entry> entryList = solution.getEntryList();
		assertEquals(courseList.size(), entryList.size());
		List<Long> startDateListCalculated = new ArrayList<Long>();
		
		for (Entry e : entryList) {
			// Check for courses which start on the same date
			Long currDate = (Long) e.getStartDate().getTime();
			boolean listContainsDate = startDateListCalculated.contains(currDate);
			assertFalse("Overlapping.", listContainsDate);
			if (!listContainsDate) {
				startDateListCalculated.add(currDate);
			}
		}
	}
	
	/**
	 * Simple test method for the schedular that takes into account different 
	 * teachers.
	 * 
	 * Case: 4 courses need to be scheduled in 4 date slots (with 2 pairs of slots
	 * at the same time) while having only 2 teachers.
	 * 
	 * Test passes if all the courses have been assigned pairwise to the slots with no
	 * overlap in the teachers agenda.
	 */
	@Test
	public void simpleSchedulingWithTeachers() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(new Date(2014, 3, 24, 8, 0, 0));
		startDateList.add(new Date(2014, 3, 24, 8, 0, 0));
		startDateList.add(new Date(2014, 3, 24, 10, 0, 0));
		startDateList.add(new Date(2014, 3, 24, 10, 0, 0));
		
		// RoomList
		Room room = new Room();
		room.setCapacity(30);
		room.setProjectorEquipped(false);
		room.setType(RoomType.ClassRoom);
		List<Room> roomList = new ArrayList<Room>();
		roomList.add(room);
		roomList.add(room);
		roomList.add(room);
		roomList.add(room);
		
		// Couse list
		List<Course> courseList = new ArrayList<Course>();
		Course course = new Course();
		course.
		courseList.add(course);
		courseList.add(course);
		courseList.add(course);
		courseList.add(course);
		SchedularSolver solver = new SchedularSolver(startDateList, roomList, courseList);
		Schedular solution = solver.run();
		
		
		/*
		 * Verify solution: check if there are is no overlap on the startdate of the
		 * courses.
		 */
		List<Entry> entryList = solution.getEntryList();
		assertEquals(courseList.size(), entryList.size());
		List<Long> startDateListCalculated = new ArrayList<Long>();
		
		for (Entry e : entryList) {
			// Check for courses which start on the same date
			Long currDate = (Long) e.getStartDate().getTime();
			boolean listContainsDate = startDateListCalculated.contains(currDate);
			assertFalse("Overlapping.", listContainsDate);
			if (!listContainsDate) {
				startDateListCalculated.add(currDate);
			}
		}
	}
}
