package com.vub.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.CourseEnrollmentAssociation;
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
			room.setCapacity(40*(i + 1));
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

	/**
	 * @author youri
	 * 
	 * Test method where the focus lies on the constraint "roomCapacity" to schedule lectures in the right room.
	 * 
	 * Case: 3 courses followed by respectively 24, 86 and 152 students have to be scheduled at the same time.
	 * The assumption is made that all the students only follow one of these 3 courses, so there is no overlap.
	 * There are 3 rooms with each a different capacity: 36, 106 and 153. 
	 * 
	 * The test passes when each course received a room with enough capacity to seat all the students.
	 */
	@Test
	public void roomAllocationByCapacity(){
		//startDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(new Date(2014, 3, 24, 8, 0, 0));

		// RoomList
		List<Room> roomList = new ArrayList<Room>();
		Room room1 = new Room();
		Room room2 = new Room();
		Room room3 = new Room();
		room1.setCapacity(36);
		room2.setCapacity(106);
		room3.setCapacity(153);
		roomList.add(room1);
		roomList.add(room2);
		roomList.add(room3);

		for (Room r:roomList) {
			r.setProjectorEquipped(false);
			r.setType(RoomType.ClassRoom);
		}

		// Course list
		// Init 3 teachers
		User teacher1 = new User();
		teacher1.setUsername("Tim");
		User teacher2 = new User();
		teacher2.setUsername("Pieter");
		User teacher3 = new User();
		teacher3.setUsername("Youri");

		CourseTeacherAssociation courseTeacherAss1 = new CourseTeacherAssociation();
		courseTeacherAss1.setUser(teacher1);
		List<CourseTeacherAssociation> teachers1 = new ArrayList<CourseTeacherAssociation>();
		teachers1.add(courseTeacherAss1);
		CourseTeacherAssociation courseTeacherAss2 = new CourseTeacherAssociation();
		courseTeacherAss2.setUser(teacher2);
		List<CourseTeacherAssociation> teachers2 = new ArrayList<CourseTeacherAssociation>();
		teachers2.add(courseTeacherAss2);
		CourseTeacherAssociation courseTeacherAss3 = new CourseTeacherAssociation();
		courseTeacherAss3.setUser(teacher3);
		List<CourseTeacherAssociation> teachers3 = new ArrayList<CourseTeacherAssociation>();
		teachers3.add(courseTeacherAss3);

		// Init 3 courses
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		Course course1 = new Course();
		Course course2 = new Course();
		Course course3 = new Course();

		List<CourseComponent> courseComponents1 = new ArrayList<CourseComponent>();
		List<CourseComponent> courseComponents2 = new ArrayList<CourseComponent>();
		List<CourseComponent> courseComponents3 = new ArrayList<CourseComponent>();
		CourseComponent courseHOC1 = new CourseComponent();
		CourseComponent courseHOC2 = new CourseComponent();
		CourseComponent courseHOC3 = new CourseComponent();
		courseHOC1.setTeachers(teachers1);
		courseHOC2.setTeachers(teachers2);
		courseHOC3.setTeachers(teachers3);
		courseHOC1.setCourse(course1);
		courseHOC2.setCourse(course2);
		courseHOC3.setCourse(course3);
		courseComponents1.add(courseHOC1);
		courseComponents2.add(courseHOC2);
		courseComponents3.add(courseHOC3);
		course1.setCourseComponents(courseComponents1);
		course2.setCourseComponents(courseComponents2);
		course3.setCourseComponents(courseComponents3);
		courseComponentList.add(courseHOC1);
		courseComponentList.add(courseHOC2);
		courseComponentList.add(courseHOC3);

		// Students for each course
		List<CourseEnrollmentAssociation> subscriptions1 = new ArrayList<CourseEnrollmentAssociation>();
		List<CourseEnrollmentAssociation> subscriptions2 = new ArrayList<CourseEnrollmentAssociation>();
		List<CourseEnrollmentAssociation> subscriptions3 = new ArrayList<CourseEnrollmentAssociation>();
		
		for(int i = 0; i < 24; ++i){
			User user = new User();
			user.setUsername(Integer.toString(i));
			CourseEnrollmentAssociation assoc = new CourseEnrollmentAssociation();
			assoc.setUser(user);
			assoc.setCourse(course1);
			subscriptions1.add(assoc);
		}
		for(int i = 0; i < 86; ++i){
			User user = new User();
			user.setUsername(Integer.toString(-i));
			CourseEnrollmentAssociation assoc = new CourseEnrollmentAssociation();
			assoc.setUser(user);
			assoc.setCourse(course2);
			subscriptions2.add(assoc);
		}
		for(int i = 0; i < 152; ++i){
			User user = new User();
			user.setUsername(Integer.toString(1000+i));
			CourseEnrollmentAssociation assoc = new CourseEnrollmentAssociation();
			assoc.setUser(user);
			assoc.setCourse(course3);
			subscriptions3.add(assoc);
		}
		course1.setUsers(subscriptions1);
		course2.setUsers(subscriptions2);
		course3.setUsers(subscriptions3);
		
		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				courseComponentList);
		Schedular solution = solver.run();
		
		List<Entry> entryList = solution.getEntryList();
		assertEquals(courseComponentList.size(), entryList.size());
		logger.info("Unit test Room Allocation by capacity: ");
		for (Entry e : entryList) {
			logger.info(e.toString());
		}
		assertTrue(checkRoomsEnoughCapacity(solution));
		
	}
	
	private boolean checkRoomsEnoughCapacity(Schedular solution){
		boolean f = true;
		for(Entry e : solution.getEntryList()){
			int roomCapacity = e.getRoom().getCapacity();
			int numberOfStudents = e.getCourseComponent().getCourse().getUsers().size();
			if(roomCapacity < numberOfStudents){
				f = false;
				break;
			}
		}
		return f;
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
