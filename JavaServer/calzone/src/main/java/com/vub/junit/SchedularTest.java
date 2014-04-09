package com.vub.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
		roomList.add(createRoom());

		// Course list
		User teacher1 = new User();
		teacher1.setUsername("Tim");
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 4; ++i) {
			courseComponentList.add(createCourseComponent(teacher1));
		}

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				courseComponentList);
		Schedular solution = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses.
		 */
		List<Entry> entryList = solution.getEntryList();
		assertEquals("Missing entries for number of courses.",
				courseComponentList.size(), entryList.size());
		logger.info("Unit test Simple Scheduling: ");
		for (Entry e : entryList) {
			logger.info(e.toString());
		}
		List<Long> startDateListCalculated = new ArrayList<Long>();

		assertEquals("HardScore is not 0.", solution.getScore().getHardScore(),
				0);
		assertEquals("SoftScore is not 0", solution.getScore().getSoftScore(),
				0);
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
			roomList.add(createRoom());
		}

		//
		// Course list
		//
		// Init 2 teachers
		User teacher1 = new User();
		teacher1.setUsername("Tim");
		User teacher2 = new User();
		teacher2.setUsername("Pieter");

		// Init 4 courses
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		// 2 Courses with same teacher
		for (int i = 0; i < 2; i++) {
			courseComponentList.add(createCourseComponent(teacher1));
		}

		// 2 Courses with same teacher
		for (int i = 0; i < 2; i++) {
			courseComponentList.add(createCourseComponent(teacher2));
		}

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				courseComponentList);
		Schedular solution = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses and no overlap in the teachers agenda.
		 */
		List<Entry> entryList = solution.getEntryList();
		assertEquals("Missing entries for number of courses.",
				courseComponentList.size(), entryList.size());
		logger.info("Unit test Simple Scheduling with teachers: ");
		for (Entry e : entryList) {
			logger.info(e.toString());
		}

		assertEquals("HardScore is not 0.", solution.getScore().getHardScore(),
				0);
		assertEquals("SoftScore is not 0", solution.getScore().getSoftScore(),
				0);
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
				Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));
		logger.info(startDateList.toString());

		// RoomList
		List<Room> roomList = new ArrayList<Room>();
		roomList.add(createRoom());

		// Course list: start at respectively week 5, 6, 7, 8
		User teacher1 = new User();
		teacher1.setUsername("Tim");
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 4; ++i) {
			CourseComponent cc = createCourseComponent(teacher1);
			Calendar cal = Calendar.getInstance();
			// Starting date of course
			cal.set(Calendar.WEEK_OF_YEAR, 5 + i);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			cal.set(Calendar.HOUR, 8);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cc.setStartingDate(cal.getTime());
			// End date of course
			cal.set(Calendar.WEEK_OF_YEAR, 11);
			cc.setEndingDate(cal.getTime());
			
			courseComponentList.add(cc);
		}

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				courseComponentList);
		Schedular solution = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses.
		 */
		List<Entry> entryList = solution.getEntryList();
		assertEquals("Missing entries for number of courses.",
				courseComponentList.size(), entryList.size());
		logger.info("Unit test Scheduling Range Test: ");
		for (Entry e : entryList) {
			logger.info(e.toString());
		}

		assertEquals("HardScore is not 0.", solution.getScore().getHardScore(),
				0);
		assertEquals("SoftScore is not 0", solution.getScore().getSoftScore(),
				0);
		assertFalse("Overlapping", checkForOverlapTeacherAgenda(entryList));
		assertTrue("Course(s) start before start date.",
				checkForValidStartDate(entryList));
		assertTrue("Course(s) end after end date.",
				checkForValidEndDate(entryList));
	}

	/**
	 * Check for overlap in a teacher's agenda.
	 * 
	 * @param entryList
	 * @return true if there is overlap in a teacher's agenda. False otherwise.
	 * @author pieter
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
	 * @return True if all courses start after the specified start date. False
	 *         otherwise.
	 * @author pieter
	 */
	private boolean checkForValidStartDate(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getStartingDate()
					.compareTo(e.getStartDate()) > 0)
				return false;
		}
		return true;
	}

	/**
	 * @author pieter
	 * 
	 *         Checks that a course ends before the specified end date.
	 * 
	 * @param entryList
	 * @return True if all courses end before the specified end date. False
	 *         otherwise.
	 */
	private boolean checkForValidEndDate(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getEndingDate()
					.compareTo(e.getStartDate()) < 0)
				return false;
		}
		return true;
	}

	/**
	 * @author youri
	 * 
	 *         Test method where the focus lies on the constraint "roomCapacity"
	 *         to schedule lectures in the right room.
	 * 
	 *         Case: 3 courses followed by respectively 24, 86 and 152 students
	 *         have to be scheduled at the same time. The assumption is made
	 *         that all the students only follow one of these 3 courses, so
	 *         there is no overlap. There are 3 rooms with each a different
	 *         capacity: 36, 106 and 153.
	 * 
	 *         The test passes when each course received a room with enough
	 *         capacity to seat all the students.
	 */
	@Test
	public void roomAllocationByCapacity() {
		// startDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(new Date(2014, 3, 24, 8, 0, 0));

		// RoomList
		List<Room> roomList = new ArrayList<Room>();
		roomList.add(createRoom(36));
		roomList.add(createRoom(106));
		roomList.add(createRoom(153));

		for (Room r : roomList) {
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

		// Init 3 courses
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		courseComponentList.add(createCourseComponent(teacher1, 24, 2, 2));
		courseComponentList.add(createCourseComponent(teacher2, 86, 2, 2));
		courseComponentList.add(createCourseComponent(teacher3, 152, 2, 2));

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				courseComponentList);
		Schedular solution = solver.run();

		List<Entry> entryList = solution.getEntryList();
		assertEquals("Missing entries for number of courses.",
				courseComponentList.size(), entryList.size());
		logger.info("Unit test Room Allocation by capacity: ");
		for (Entry e : entryList) {
			logger.info(e.toString());
		}
		assertEquals("HardScore is not 0.", solution.getScore().getHardScore(),
				0);
		assertEquals("SoftScore is not 0", solution.getScore().getSoftScore(),
				0);
		assertTrue("Room capacity violation.", checkRoomsEnoughCapacity(solution));

	}

	private boolean checkRoomsEnoughCapacity(Schedular solution) {
		boolean f = true;
		for (Entry e : solution.getEntryList()) {
			int roomCapacity = e.getRoom().getCapacity();
			int numberOfStudents = e.getCourseComponent().getCourse()
					.getUsers().size();
			if (roomCapacity < numberOfStudents) {
				f = false;
				break;
			}
		}
		return f;
	}

	/**
	 * Creates a default Room object with parameters:
	 * <ul>
	 * <li> Capacity is 40
	 * <li> Roomtype is ClassRoom
	 * <li> ProjectorEquipped is false
	 * </ul>
	 * @return A new room object,
	 */
	private Room createRoom() {
		return createRoom(40);
	}

	private Room createRoom(int capacity) {
		Room room = new Room();
		room.setCapacity(capacity);
		room.setProjectorEquipped(false);
		room.setType(RoomType.ClassRoom);

		return room;
	}

	private CourseComponent createCourseComponent(User teacher) {
		return createCourseComponent(teacher, 20, 2, 2);
	}

	/**
	 * Creates a new CourseComponent object (connected with a course object)
	 * with initialized parameters.
	 * 
	 * @param teacher The specified teacher.
	 * @param numberOfStudents The number of students enrolled in this course.
	 * @param contactHours The total amount of hours of this course.
	 * @param duration The duration of one lecture.
	 * @return A new CourseComponent object.
	 */
	private CourseComponent createCourseComponent(User teacher,
			int numberOfStudents, int contactHours, int duration) {
		CourseTeacherAssociation courseTeacherAss1 = new CourseTeacherAssociation();
		courseTeacherAss1.setUser(teacher);
		List<CourseTeacherAssociation> teachers1 = new ArrayList<CourseTeacherAssociation>();
		teachers1.add(courseTeacherAss1);

		Course course1 = new Course();

		List<CourseComponent> courseComponents1 = new ArrayList<CourseComponent>();
		CourseComponent courseHOC1 = new CourseComponent();
		courseHOC1.setTeachers(teachers1);
		courseHOC1.setCourse(course1);
		courseHOC1.setContactHours(contactHours);
		courseHOC1.setDuration(duration);
		courseComponents1.add(courseHOC1);
		courseHOC1.setStartingDate(new Date(2013, 1, 1));
		course1.setCourseComponents(courseComponents1);

		List<CourseEnrollmentAssociation> subscriptions1 = new ArrayList<CourseEnrollmentAssociation>();

		for (int i = 0; i < numberOfStudents; ++i) {
			User user = new User();
			user.setUsername(Integer.toString(numberOfStudents));
			CourseEnrollmentAssociation assoc = new CourseEnrollmentAssociation();
			assoc.setUser(user);
			assoc.setCourse(course1);
			subscriptions1.add(assoc);
		}

		course1.setUsers(subscriptions1);

		return courseHOC1;
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
