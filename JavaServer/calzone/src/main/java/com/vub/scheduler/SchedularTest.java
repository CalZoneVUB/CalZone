package com.vub.scheduler;

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
		User teacher1 = new User();
		teacher1.setUsername("Tim");
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();
		CourseComponent courseComponent = new CourseComponent();
		CourseTeacherAssociation courseTeacherAss1 = new CourseTeacherAssociation();
		courseTeacherAss1.setUser(teacher1);
		List<CourseTeacherAssociation> teachers1 = new ArrayList<CourseTeacherAssociation>();
		teachers1.add(courseTeacherAss1);
		courseComponent.setTeachers(teachers1);
		courseComponentList.add(courseComponent);
		courseComponentList.add(courseComponent);
		courseComponentList.add(courseComponent);
		courseComponentList.add(courseComponent);
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
		Room room = new Room();
		room.setCapacity(30);
		room.setProjectorEquipped(false);
		room.setType(RoomType.ClassRoom);
		List<Room> roomList = new ArrayList<Room>();
		roomList.add(room);
		roomList.add(room);
		roomList.add(room);
		roomList.add(room);

		//
		// Couse list
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

		// Course 1
		Course course1 = new Course();
		{
			// Component HOC
			List<CourseComponent> courseComponents1 = new ArrayList<CourseComponent>();
			CourseComponent course1HOC = new CourseComponent();
			course1HOC.setTeachers(teachers1);
			course1HOC.setCourse(course1);
			courseComponents1.add(course1HOC);
			course1.setCourseComponents(courseComponents1);
			
			courseComponentList.add(course1HOC);
		}

		// Course 2 with same teacher as course 1
		Course course2 = new Course();
		{
			// Component HOC
			List<CourseComponent> courseComponents2 = new ArrayList<CourseComponent>();
			CourseComponent course2HOC = new CourseComponent();
			course2HOC.setTeachers(teachers1);
			course2HOC.setCourse(course2);
			courseComponents2.add(course2HOC);
			course2.setCourseComponents(courseComponents2);
			
			courseComponentList.add(course2HOC);
		}

		// Course 3
		Course course3 = new Course();
		{
			// Component HOC
			List<CourseComponent> courseComponents3 = new ArrayList<CourseComponent>();
			CourseComponent course3HOC = new CourseComponent();
			course3HOC.setTeachers(teachers2);
			course3HOC.setCourse(course3);
			courseComponents3.add(course3HOC);
			course3.setCourseComponents(courseComponents3);
			
			courseComponentList.add(course3HOC);
		}
		
		// Course 4 with same teacher as course 3
		Course course4 = new Course();
		{
			// Component HOC
			List<CourseComponent> courseComponent = new ArrayList<CourseComponent>();
			CourseComponent courseHOC = new CourseComponent();
			courseHOC.setTeachers(teachers2);
			courseHOC.setCourse(course4);
			courseComponent.add(courseHOC);
			course4.setCourseComponents(courseComponent);
			
			courseComponentList.add(courseHOC);
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
			String teacherName = courseHOC.getTeachers().get(0).getUser().getUsername();
			boolean listContainsPair = agendaTeacher.contains(new Pair<Date, String>(e.getStartDate(), teacherName));
			assertFalse("Overlapping.", listContainsPair);
			if (!listContainsPair) {
				agendaTeacher.add(new Pair<Date, String>(e.getStartDate(), teacherName));
			}
		}
	}
	
	private class Pair<T,V> {
		public T first;
		public V second;
		
		public Pair(T first, V second) {
			this.first = first;
			this.second = second;
		}
	}
}
