package com.vub.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.CourseTeacherAssociation;
import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.Room.RoomType;
import com.vub.model.User;
import com.vub.scheduler.Schedular;
import com.vub.scheduler.SchedularSolver;
import com.vub.scheduler.SchedulerInitializer;

/**
 * Unit test class for the schedular.
 * 
 * @author pieter
 * 
 */
public class SchedularTest {
	final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Simple test method for the schedular.
	 * 
	 * Case: 4 courses need to be scheduled in 4 date slots. There is only one
	 * room available.
	 * 
	 * Test passes if all the courses have been assigned a date slot with no
	 * overlap.
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
		startDateList.add(new Date(2014, 3, 24, 15, 0, 0));
		startDateList.add(new Date(2014, 3, 24, 10, 0, 0));
		startDateList.add(new Date(2014, 3, 24, 13, 0, 0));

		// RoomList
		List<Room> roomList = new ArrayList<Room>();
		Room room = new Room();
		room.setCapacity(40);
		room.setProjectorEquipped(false);
		room.setType(RoomType.ClassRoom);
		roomList.add(room);

		// Course list
		User teacher1 = new User();
		teacher1.setUsername("Tim");
		CourseTeacherAssociation courseTeacherAss1 = new CourseTeacherAssociation();
		courseTeacherAss1.setUser(teacher1);
		List<CourseTeacherAssociation> teachers1 = new ArrayList<CourseTeacherAssociation>();
		teachers1.add(courseTeacherAss1);
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 4; ++i) {
			Course course = new Course();
			CourseComponent courseComponent = new CourseComponent();
			courseComponent.setTeachers(teachers1);
			courseComponent.setCourse(course);
			courseComponentList.add(courseComponent);
		}

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				courseComponentList);
		Schedular solution = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses.
		 */
		List<Entry> entryList = solution.getEntryList();
		assertEquals(courseComponentList.size(), entryList.size());
		logger.info("Unit test Simple Scheduling: ");
		for (Entry e : entryList) {
			logger.info(e.toString());
		}
		List<Long> startDateListCalculated = new ArrayList<Long>();

		for (Entry e : entryList) {
			// Check for courses which start on the same date
			Long currDate = (Long) e.getStartDate().getTime();
			boolean listContainsDate = startDateListCalculated
					.contains(currDate);
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
	 * Case: 4 courses need to be scheduled in 2 date slots while having only 2
	 * teachers. The courses contain only HOC as course component types.
	 * Furthermore there are only 2 different rooms.
	 * 
	 * Test passes if all the courses have been assigned pairwise to the slots
	 * with no overlap in the teachers agenda.
	 */
	@Test
	public void simpleSchedulingWithTeachers() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(new Date(2014, 3, 24, 8, 0, 0));
		startDateList.add(new Date(2014, 3, 24, 10, 0, 0));

		// RoomList
		List<Room> roomList = new ArrayList<Room>();
		for (int i = 0; i < 2; ++i) {
			Room room = new Room();
			room.setCapacity(30 * (i + 1));
			room.setProjectorEquipped(false);
			room.setType(RoomType.ClassRoom);
			roomList.add(room);
		}

		//
		// Course list
		//
		// Init 2 teachers
		User teacher1 = new User();
		teacher1.setUsername("Tim");
		User teacher2 = new User();
		teacher2.setUsername("Pieter");

		CourseTeacherAssociation courseTeacherAss1 = new CourseTeacherAssociation();
		courseTeacherAss1.setUser(teacher1);
		List<CourseTeacherAssociation> teachers1 = new ArrayList<CourseTeacherAssociation>();
		teachers1.add(courseTeacherAss1);
		CourseTeacherAssociation courseTeacherAss2 = new CourseTeacherAssociation();
		courseTeacherAss2.setUser(teacher2);
		List<CourseTeacherAssociation> teachers2 = new ArrayList<CourseTeacherAssociation>();
		teachers2.add(courseTeacherAss2);

		// Init 4 courses
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		// 2 Courses with same teacher
		for (int i = 0; i < 2; i++) {
			Course course = new Course();
			// Component HOC
			List<CourseComponent> courseComponents = new ArrayList<CourseComponent>();
			CourseComponent courseHOC = new CourseComponent();
			courseHOC.setTeachers(teachers1);
			courseHOC.setCourse(course);
			courseComponents.add(courseHOC);
			course.setCourseComponents(courseComponents);

			courseComponentList.add(courseHOC);
		}

		// 2 Courses with same teacher
		for (int i = 0; i < 2; i++) {
			Course course = new Course();
			{
				// Component HOC
				List<CourseComponent> courseComponents = new ArrayList<CourseComponent>();
				CourseComponent courseHOC = new CourseComponent();
				courseHOC.setTeachers(teachers2);
				courseHOC.setCourse(course);
				courseComponents.add(courseHOC);
				course.setCourseComponents(courseComponents);

				courseComponentList.add(courseHOC);
			}
		}

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				courseComponentList);
		Schedular solution = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses and no overlap in the teachers agenda.
		 */
		List<Entry> entryList = solution.getEntryList();
		assertEquals(courseComponentList.size(), entryList.size());
		logger.info("Unit test Simple Scheduling with teachers: ");
		for (Entry e : entryList) {
			logger.info(e.toString());
		}
		
		
		assertFalse("Overlapping.", checkForOverlapTeacherAgenda(entryList));
	}

	/**
	 * Test for testing rules "courseStartsBeforeStartDate" and
	 * "courseEndsAfterEndDate".
	 * 
	 * Case: 4 courses need to be scheduled in many available date slots. There
	 * is only one room available. The date slots starts before the start date
	 * of the course and end after the end date of the course.
	 * 
	 * Test passes if all the courses have been assigned a date slot with no
	 * overlap and the courses have been assigned to a date slot that lies in
	 * their available range.
	 * 
	 */
	@Test
	public void schedulingRangeTest() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = SchedulerInitializer.createSlotsOfTerm(2014,
				Arrays.asList(1, 2, 3, 4, 5, 6));

		// RoomList
		List<Room> roomList = new ArrayList<Room>();
		Room room = new Room();
		room.setCapacity(40);
		room.setProjectorEquipped(false);
		room.setType(RoomType.ClassRoom);
		roomList.add(room);

		// Course list
		User teacher1 = new User();
		teacher1.setUsername("Tim");
		CourseTeacherAssociation courseTeacherAss1 = new CourseTeacherAssociation();
		courseTeacherAss1.setUser(teacher1);
		List<CourseTeacherAssociation> teachers1 = new ArrayList<CourseTeacherAssociation>();
		teachers1.add(courseTeacherAss1);
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 4; ++i) {
			Course course = new Course();
			CourseComponent courseComponent = new CourseComponent();
			courseComponent.setTeachers(teachers1);
			courseComponent.setCourse(course);
			courseComponentList.add(courseComponent);
		}

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				courseComponentList);
		Schedular solution = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses.
		 */
		List<Entry> entryList = solution.getEntryList();
		assertEquals(courseComponentList.size(), entryList.size());
		logger.info("Unit test Simple Scheduling: ");
		for (Entry e : entryList) {
			logger.info(e.toString());
		}
		
		assertFalse("Overlapping", checkForOverlapTeacherAgenda(entryList));
		assertTrue("Course(s) start before start date.", checkForValidStartDate(entryList));
		assertTrue("Course(s) end after end date.", checkForValidEndDate(entryList));
	}
	
	/**
	 * Check for overlap in a teacher's agenda.
	 * 
	 * @param entryList
	 * @return true if there is overlap in a teacher's agenda. False otherwise.
	 */
	private boolean checkForOverlapTeacherAgenda(List<Entry> entryList) {
		List<Pair<Date, String>> agendaTeacher = new ArrayList<Pair<Date, String>>();
		for (Entry e : entryList) {
			CourseComponent courseHOC = e.getCourseComponent();
			String teacherName = courseHOC.getTeachers().get(0).getUser()
					.getUsername();
			boolean listContainsPair = agendaTeacher
					.contains(new Pair<Date, String>(e.getStartDate(),
							teacherName));
			if (!listContainsPair) {
				agendaTeacher.add(new Pair<Date, String>(e.getStartDate(),
						teacherName));
			} else {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks that a course starts after the specified start date.
	 * 
	 * @param entryList
	 * @return True if all courses start after the specified start date. False otherwise.
	 */
	private boolean checkForValidStartDate(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getStartingDate().compareTo(e.getStartDate()) > 0)
					return false;
		}
		return true;
	}
	
	/**
	 * Checks that a course ends before the specified end date.
	 * 
	 * @param entryList
	 * @return True if all courses end before the specified end date. False otherwise.
	 */
	private boolean checkForValidEndDate(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getEndDate().compareTo(e.getStartDate()) < 0)
				return false;
		}
		return true;
	}

	private class Pair<T, V> {
		public T first;
		public V second;

		public Pair(T first, V second) {
			this.first = first;
			this.second = second;
		}
	}
}
