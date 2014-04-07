package com.vub.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
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
	 * Case: 4 courses need to be scheduled in 4 date slots.
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
		for (int i = 0; i < 4; ++i) {
			Room room = new Room();
			room.setCapacity(30 * (i + 1));
			room.setProjectorEquipped(false);
			room.setType(RoomType.ClassRoom);
			roomList.add(room);
		}

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
		for (int i = 0; i < 4; ++i) {
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

		// Check for overlap in the teacher's agenda
		List<Pair<Date, String>> agendaTeacher = new ArrayList<Pair<Date, String>>();
		for (Entry e : entryList) {
			CourseComponent courseHOC = e.getCourseComponent();
			String teacherName = courseHOC.getTeachers().get(0).getUser()
					.getUsername();
			boolean listContainsPair = agendaTeacher
					.contains(new Pair<Date, String>(e.getStartDate(),
							teacherName));
			assertFalse("Overlapping.", listContainsPair);
			if (!listContainsPair) {
				agendaTeacher.add(new Pair<Date, String>(e.getStartDate(),
						teacherName));
			}
		}
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
