package com.vub.scheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vub.junit.ExtendedRunner;
import com.vub.junit.Repeat;
import com.vub.model.CourseComponent;
import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.Room.RoomType;
import com.vub.model.Traject;
import com.vub.model.User;

/**
 * Unit test class for the schedular. This class tests the different rules
 * implemented in SchedulerScoreRules.drl.
 * 
 * <p>
 * The main purpose of this class is to test the correctnes of these different
 * rules.
 * 
 * Most of the unit tests is run multiple times, this is because selectionOrder
 * in the solver is random.
 * </p>
 * <p>
 * The unit tests are executed using a custom runner, implemented in
 * {@link ExtendedRunner ExtendedRunner}.
 * </p>
 * 
 * @author Pieter Meiresone
 * @author Youri Coppens
 * 
 */
@RunWith(ExtendedRunner.class)
public class SchedularSolverTest {
	final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Simple test method for the schedular.
	 * 
	 * Case: 4 courses (with the same teacher) need to be scheduled in 4 date
	 * slots. There is only one room available.
	 * 
	 * Test passes if all the courses have been assigned a date slot with no
	 * overlap.
	 * 
	 */
	@Test
	@Repeat(10)
	public void overlappingTeacherAgendaExplicit() {
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
		List<Room> roomList = Arrays.asList(Helper.createRoom());

		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 4; ++i) {
			CourseComponent cc = Helper.createCourseComponent(teachers);
			courseComponentList.add(cc);
		}

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				Helper.createTraject(courseComponentList));
		Schedular sol = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses.
		 */
		List<Entry> entryList = sol.getEntryList();
		assertEquals("Missing entries for number of courses.",
				expectedSizeEntryList(courseComponentList), entryList.size());
		logEntries("overlappingTeacherAgendaExplicit", entryList);

		assertEquals("HardScore is not 0.", sol.getScore().getHardScore(), 0);
		assertEquals("SoftScore is not 0", sol.getScore().getSoftScore(), 0);
		assertFalse("Overlap in agenda.",
				checkForOverlapTeacherAgenda(entryList));
	}

	/**
	 * Case: 4 courses need to be scheduled in 8 date slots during the same day.
	 * The available slots are 8, 9, 10, 11, 12, 13, 14 and 15 hour. There is
	 * only one room available.
	 * 
	 * Test passes if all the courses have been assigned a date slot with no
	 * overlap.
	 * 
	 */
	@Test
	@Repeat(10)
	public void overlappingTeacherAgendaImplicit() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		for (int hourOfDay : Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15)) {
			startDateList.add(new Date(2014, 3, 24, hourOfDay, 0, 0));
		}

		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom());

		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 4; ++i) {
			courseComponentList.add(Helper.createCourseComponent(teachers));
		}

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				Helper.createTraject(courseComponentList));
		Schedular sol = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses.
		 */
		List<Entry> entryList = sol.getEntryList();
		assertEquals("Missing entries for number of courses.",
				expectedSizeEntryList(courseComponentList), entryList.size());
		logEntries("overlappingTeacherAgendaImplicit", entryList);

		assertEquals("HardScore is not 0.", 0, sol.getScore().getHardScore());
		assertEquals("SoftScore is not 0", 0, sol.getScore().getSoftScore());
		assertFalse("Overlapping in teacher agenda.",
				checkForOverlapTeacherAgenda(entryList));
	}

	/**
	 * 2 trajects, each consisting of 4 courses need to be scheduled. There are
	 * 4 date slots, 2 rooms available. Each traject is given by one teacher.
	 * 
	 * Test method for the rule "overlappingStudentAgenda".
	 */
	@Test
	@Repeat(10)
	public void overlappingStudentAgenda() {
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
		List<Room> roomList = Arrays.asList(Helper.createRoom(),
				Helper.createRoom());

		HashSet<User> teachers1 = Helper.createTeachers("Tim");
		HashSet<User> teachers2 = Helper.createTeachers("Pieter");

		Set<Traject> trajectList = new HashSet<Traject>();
		List<CourseComponent> ccList1 = new ArrayList<CourseComponent>();
		for (int i = 0; i < 4; ++i) {
			CourseComponent cc = Helper.createCourseComponent(teachers1);
			ccList1.add(cc);
		}
		trajectList.addAll(Helper.createTraject(ccList1));
		List<CourseComponent> ccList2 = new ArrayList<CourseComponent>();
		for (int i = 0; i < 4; ++i) {
			CourseComponent cc = Helper.createCourseComponent(teachers2);
			ccList2.add(cc);
		}
		trajectList.addAll(Helper.createTraject(ccList2));

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				trajectList);
		Schedular sol = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses.
		 */
		List<Entry> entryList = sol.getEntryList();
		assertEquals("Missing entries for number of courses.",
				8, entryList.size());
		logEntries("overlappingStudentAgenda", entryList);

		assertEquals("HardScore is not 0.", sol.getScore().getHardScore(), 0);
		assertEquals("SoftScore is not 0", sol.getScore().getSoftScore(), 0);
		assertFalse("Overlap in agenda.",
				checkForOverlapStudentAgenda(trajectList, entryList));
	}

	/**
	 * Simple test method for the schedular that takes into account different
	 * teachers.
	 * 
	 * Case: 4 courses need to be scheduled in 2 date slots while having only 2
	 * teachers. The courses contain only HOC as course component types.
	 * Furthermore there are only 2 different rooms.
	 * 
	 * To reduce overhead, each coursecomponent is part of a different traject.
	 * So overlap in the student agenda is not possible.
	 * 
	 * Test passes if all the courses have been assigned pairwise to the slots
	 * with no overlap in the teachers agenda.
	 */
	@Test
	@Repeat(10)
	public void simpleSchedulingWithTeachers() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(new Date(2014, 3, 24, 8, 0, 0));
		startDateList.add(new Date(2014, 3, 24, 10, 0, 0));

		// RoomList
		List<Room> roomList = Arrays.asList(Helper.createRoom(),
				Helper.createRoom());

		// Course list
		// Init 2 teachers
		HashSet<User> teachers1 = Helper.createTeachers("Tim");
		HashSet<User> teachers2 = Helper.createTeachers("Pieter");

		// Init 4 courses
		Set<Traject> trajectList = new HashSet<Traject>();

		// 2 Courses with same teacher
		for (int i = 0; i < 2; i++) {
			trajectList.addAll(Helper.createTraject(Helper
					.createCourseComponent(teachers1)));
		}

		// 2 Courses with same teacher
		for (int i = 0; i < 2; i++) {
			trajectList.addAll(Helper.createTraject(Helper
					.createCourseComponent(teachers2)));
		}

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				trajectList);
		Schedular sol = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses and no overlap in the teachers agenda.
		 */
		List<Entry> entryList = sol.getEntryList();
		assertEquals("Missing entries for number of courses.", 4,
				entryList.size());
		logEntries("simpleSchedulingWithTeachers", entryList);

		assertEquals("HardScore is not 0.", sol.getScore().getHardScore(), 0);
		assertEquals("SoftScore is not 0", sol.getScore().getSoftScore(), 0);
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
	@Repeat(10)
	public void schedulingRangeTest() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = SchedulerInitializer.createSlotsOfTerm(2014,
				Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));

		// RoomList
		List<Room> roomList = new ArrayList<Room>();
		roomList.add(Helper.createRoom());

		// Course list: start at respectively week 5, 6, 7, 8
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 4; ++i) {
			CourseComponent cc = Helper.createCourseComponent(teachers);
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
				Helper.createTraject(courseComponentList));
		Schedular sol = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses.
		 */
		List<Entry> entryList = sol.getEntryList();
		assertEquals("Missing entries for number of courses.",
				expectedSizeEntryList(courseComponentList), entryList.size());
		logEntries("schedulingRangeTest", entryList);

		assertEquals("HardScore is not 0.", sol.getScore().getHardScore(), 0);
		assertEquals("SoftScore is not 0", sol.getScore().getSoftScore(), 0);
		assertFalse("Overlapping in agenda Teacher",
				checkForOverlapTeacherAgenda(entryList));
		assertTrue("Course(s) start before start date.",
				checkForValidStartDate(entryList));
		assertTrue("Course(s) end after end date.",
				checkForValidEndDate(entryList));
	}

	/**
	 * Test method where the focus lies on the constraint "roomCapacity" to
	 * schedule lectures in the right room.
	 * 
	 * <p>
	 * Case: 3 courses followed by respectively 24, 86 and 152 students have to
	 * be scheduled at the same time. The assumption is made that all the
	 * students only follow one of these 3 courses, so there is no overlap.
	 * There are 3 rooms with each a different capacity: 36, 106 and 153.
	 * 
	 * The test passes when each course received a room with enough capacity to
	 * seat all the students.
	 * </p>
	 * 
	 * @author youri
	 */
	@Test
	@Repeat(10)
	public void roomAllocationByCapacity() {
		// startDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(new Date(2014, 3, 24, 8, 0, 0));

		// RoomList
		List<Room> roomList = new ArrayList<Room>();
		roomList.add(Helper.createRoom(36));
		roomList.add(Helper.createRoom(106));
		roomList.add(Helper.createRoom(153));

		// Course list
		// Init 3 teachers
		HashSet<User> teachers1 = Helper.createTeachers("Tim");
		HashSet<User> teachers2 = Helper.createTeachers("Pieter");
		HashSet<User> teachers3 = Helper.createTeachers("Youri");

		// Init 3 courses
		Set<Traject> trajectList = new HashSet<Traject>();

		trajectList.addAll(Helper.createTraject(Helper.createCourseComponent(
				teachers1, 24, 2, 2, CourseComponentType.HOC)));
		trajectList.addAll(Helper.createTraject(Helper.createCourseComponent(
				teachers2, 86, 2, 2, CourseComponentType.HOC)));
		trajectList.addAll(Helper.createTraject(Helper.createCourseComponent(
				teachers3, 152, 2, 2, CourseComponentType.HOC)));

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				trajectList);
		Schedular sol = solver.run();

		List<Entry> entryList = sol.getEntryList();
		assertEquals("Missing entries for number of courses.", 3,
				entryList.size());
		logEntries("roomAllocationByCapacity", entryList);

		assertEquals("HardScore is not 0.", 0, sol.getScore().getHardScore());
		assertEquals("SoftScore is not 0.", 0, sol.getScore().getSoftScore());
		assertTrue("Room capacity violation.", checkRoomsEnoughCapacity(sol));

	}

	/**
	 * Test method for the rule 'preventAdjacentLecturesOfSameCourseComponent'.
	 * 
	 * <p>
	 * Case: 2 coursecomponents (with the same teacher) need to be scheduled in
	 * 4 date slots. There is only one room available. The coursecomponents
	 * consist of 4 hour of contacthours each, in blocks of 2 hours.
	 * 
	 * Test passes if all the courses have been assigned a date slot with no
	 * overlap.
	 * </p>
	 * 
	 * @author Pieter Meiresone
	 */
	@Test
	@Repeat(10)
	public void preventAdjacentLecturesOfSameCourseComponent() {
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
		List<Room> roomList = Arrays.asList(Helper.createRoom());

		// Course list
		HashSet<User> teachers = Helper.createTeachers("Tim");
		List<CourseComponent> courseComponentList = new ArrayList<CourseComponent>();

		for (int i = 0; i < 2; ++i) {
			courseComponentList.add(Helper.createCourseComponent(teachers, 20,
					4, 2, CourseComponentType.HOC));
		}

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				Helper.createTraject(courseComponentList));
		Schedular sol = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses.
		 */
		List<Entry> entryList = sol.getEntryList();
		assertEquals("Missing entries for number of courses.",
				expectedSizeEntryList(courseComponentList), entryList.size());
		logEntries("preventAdjacentLecturesOfSameCourseComponent", entryList);

		assertEquals("HardScore is not 0.", 0, sol.getScore().getHardScore());
		assertEquals("SoftScore is not 0", 0, sol.getScore().getSoftScore());
		assertFalse("Overlap in agenda.",
				checkForOverlapTeacherAgenda(entryList));
		assertFalse("Adjacent coursecomponents.",
				checkForAdjacentCourseComponent(entryList));
	}

	/**
	 * Test method for testing rules 'roomType', 'roomEquipmentProjector',
	 * 'roomEquipmentRecorder' and 'roomEquipmentSmartBoard'
	 * 
	 * @author Pieter Meiresone
	 */
	@Test
	@Repeat(10)
	public void correctRoomType() {
		/*
		 * Solve test case
		 */
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		startDateList.add(new Date(2014, 3, 24, 8, 0, 0));

		// RoomList
		Room rmWithProjector = Helper.createRoom(40, true, false, false,
				RoomType.ClassRoom);
		Room rmWithSmartBoard = Helper.createRoom(40, false, false, true,
				RoomType.ClassRoom);
		Room rmWithRecorder = Helper.createRoom(40, false, true, false,
				RoomType.ClassRoom);
		Room rmComputer = Helper.createRoom(40, true, false, false,
				RoomType.ComputerRoom);
		List<Room> roomList = Arrays.asList(rmWithProjector, rmWithSmartBoard,
				rmWithRecorder, rmComputer);

		// Course list
		HashSet<User> teachers1 = Helper.createTeachers("Tim");
		HashSet<User> teachers2 = Helper.createTeachers("Pieter");
		HashSet<User> teachers3 = Helper.createTeachers("Youri");
		HashSet<User> teachers4 = Helper.createTeachers("Nico");

		Set<Traject> trajectList = new HashSet<Traject>();
		trajectList.addAll(Helper.createTraject(Arrays.asList(Helper
				.createCourseComponent(teachers1, 20, 2, 2,
						CourseComponentType.HOC, true, false, false,
						RoomType.ClassRoom))));
		trajectList.addAll(Helper.createTraject(Arrays.asList(Helper
				.createCourseComponent(teachers2, 20, 2, 2,
						CourseComponentType.HOC, false, true, false,
						RoomType.ClassRoom))));
		trajectList.addAll(Helper.createTraject(Arrays.asList(Helper
				.createCourseComponent(teachers3, 20, 2, 2,
						CourseComponentType.HOC, false, false, true,
						RoomType.ClassRoom))));
		trajectList.addAll(Helper.createTraject(Arrays.asList(Helper
				.createCourseComponent(teachers4, 20, 2, 2,
						CourseComponentType.HOC, false, false, false,
						RoomType.ComputerRoom))));

		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				trajectList);
		Schedular sol = solver.run();

		/*
		 * Verify solution: check if there are is no overlap on the startdate of
		 * the courses.
		 */
		List<Entry> entryList = sol.getEntryList();
		assertEquals("Missing entries for number of courses.", 4,
				entryList.size());
		logEntries("correctRoomType", entryList);

		assertEquals("HardScore is not 0.", 0, sol.getScore().getHardScore());
		assertEquals("SoftScore is not 0", 0, sol.getScore().getSoftScore());
		assertFalse("Overlap in agenda.",
				checkForOverlapTeacherAgenda(entryList));
		assertTrue("Room type violation.", checkRoomType(entryList));
		assertTrue("Room equipment projector violation.",
				checkRoomEquipmentProjector(entryList));
		assertTrue("Room equipment recorder violation.",
				checkRoomEquipmentRecorder(entryList));
		assertTrue("Room equipment smartboard violation.",
				checkRoomEquipmentSMARTBoard(entryList));
	}
	/**
	 * Scheduling of one week of courses.
	 * 
	 * <p>
	 * Following courses are scheduled:
	 * <ul>
	 * <li>Mechanica
	 * <ul>
	 * <li>2x2u HOC
	 * <li>4u WPO
	 * </ul>
	 * <li>Analyse
	 * <ul>
	 * <li>2x2u HOC
	 * <li>4u WPO
	 * </ul>
	 * <li>Algebra
	 * <ul>
	 * <li>2x2u HOC
	 * <li>4u WPO
	 * </ul>
	 * <li>Chemie
	 * <ul>
	 * <li>2x2u HOC
	 * <li>4u WPO
	 * </ul>
	 * <li>Informatica
	 * <ul>
	 * <li>2u HOC
	 * <li>4u WPO
	 * <li>Computerroom required
	 * </ul>
	 * </ul>
	 * </p>
	 * 
	 */
	@Test
	@Repeat(10)
	@Ignore
	public void advancedScheduling() {
		List<Date> startDateList = SchedulerInitializer.createSlotsOfWeek(2014,
				5);

		Room standardRoom = Helper.createRoom(40, true, true, true,
				RoomType.ClassRoom);
		Room computerRoom = Helper.createRoom(40, true, true, true,
				RoomType.ComputerRoom);
		List<Room> roomList = Arrays.asList(standardRoom, computerRoom);

		// Create Courses
		HashSet<User> tchMech = Helper.createTeachers("Dirk Lefeber");
		HashSet<User> tchAnalyse = Helper.createTeachers("Stefaan Canepeel");
		HashSet<User> tchAlgebra = Helper.createTeachers("Philippe Cara");
		HashSet<User> tchChemie = Helper.createTeachers("Rudi Whillem");
		HashSet<User> tchInformatica = Helper.createTeachers("Ann Dooms");

		List<CourseComponent> ccList = new ArrayList<CourseComponent>();

		ccList.add(Helper.createCourseComponent(tchMech, 30, 4, 2,
				CourseComponentType.HOC));
		ccList.add(Helper.createCourseComponent(tchMech, 30, 4, 4,
				CourseComponentType.WPO));
		ccList.add(Helper.createCourseComponent(tchAnalyse, 30, 4, 2,
				CourseComponentType.HOC));
		ccList.add(Helper.createCourseComponent(tchAnalyse, 30, 4, 4,
				CourseComponentType.WPO));
		ccList.add(Helper.createCourseComponent(tchAlgebra, 30, 4, 2,
				CourseComponentType.HOC));
		ccList.add(Helper.createCourseComponent(tchAlgebra, 30, 4, 4,
				CourseComponentType.WPO));
		ccList.add(Helper.createCourseComponent(tchChemie, 30, 4, 2,
				CourseComponentType.HOC));
		ccList.add(Helper.createCourseComponent(tchChemie, 30, 4, 2,
				CourseComponentType.WPO));
		ccList.add(Helper.createCourseComponent(tchInformatica, 30, 2, 2,
				CourseComponentType.HOC));
		ccList.add(Helper.createCourseComponent(tchInformatica, 30, 4, 4,
				CourseComponentType.WPO, false, false, false,
				RoomType.ComputerRoom));

		Set<Traject> trajectSet = Helper.createTraject(ccList);
		SchedularSolver solver = new SchedularSolver(startDateList, roomList,
				trajectSet);
		Schedular sol = solver.run();

		// Verify solution
		List<Entry> entryList = sol.getEntryList();
		assertEquals("Missing entries for number of courses.",
				expectedSizeEntryList(ccList), entryList.size());
		logEntries("advancedScheduling", entryList);

		assertEquals("HardScore is not 0.", 0, sol.getScore().getHardScore());
		assertEquals("SoftScore is not 0", 0, sol.getScore().getSoftScore());
		assertFalse("Overlap in teacher agenda.",
				checkForOverlapTeacherAgenda(entryList));
		assertFalse("Adjacent coursecomponents.",
				checkForAdjacentCourseComponent(entryList));
		assertTrue("Room type violation.", checkRoomType(entryList));
		assertTrue("Room equipment projector violation.",
				checkRoomEquipmentProjector(entryList));
		assertTrue("Room equipment recorder violation.",
				checkRoomEquipmentRecorder(entryList));
		assertTrue("Room equipment smartboard violation.",
				checkRoomEquipmentSMARTBoard(entryList));
		assertFalse("Overlap in agenda.",
				checkForOverlapStudentAgenda(trajectSet, entryList));
	}

	/**
	 * Method for calculating the expected size of the entry list based on the
	 * different coursecomponents.
	 * 
	 * @param ccList
	 *            The course component list.
	 * @return the expected size of the entry list.
	 * 
	 * @author pieter
	 */
	private int expectedSizeEntryList(List<CourseComponent> ccList) {
		int expectedSize = 0;

		for (CourseComponent c : ccList) {
			expectedSize += c.getContactHours() / c.getDuration();

			if ((c.getContactHours() % c.getDuration()) != 0) {
				expectedSize++;
			}
		}
		return expectedSize;
	}

	/**
	 * Check for adjacent coursecomponents in the entry list.
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if there are adjacent coursecomponenets, false otherwise.
	 */
	private boolean checkForAdjacentCourseComponent(List<Entry> entryList) {
		for (Entry e1 : entryList) {
			Date endDateCourse = Entry.calcEndDate(e1);
			for (Entry e2 : entryList) {
				if (!e1.equals(e2)
						&& e1.getCourseComponent().equals(
								e2.getCourseComponent())
						&& endDateCourse.compareTo(e2.getStartingDate()) == 0) {
					return true;
				}
			}
		}
		// TODO : werkt nog niet met springuren (alsook in rule niet)
		return false;
	}

	/**
	 * Check for overlap in a teacher's agenda.
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return true if there is overlap in a teacher's agenda. False otherwise.
	 */
	private boolean checkForOverlapTeacherAgenda(List<Entry> entryList) {
		List<Pair<Long, String>> agendaTeacher = new ArrayList<Pair<Long, String>>();
		for (Entry e : entryList) {
			CourseComponent cc = e.getCourseComponent();
			String teacherName = cc.getTeachers().iterator().next()
					.getUsername();
			Long currDateStart = (Long) e.getStartingDate().getTime();
			Long currDateEnd = (Long) Entry.calcEndDate(e).getTime();

			for (Pair<Long, String> otherPair : agendaTeacher) {
				if (teacherName.equals(otherPair.second)
						&& currDateStart <= otherPair.first
						&& currDateEnd > otherPair.first) {
					return true;
				}
			}
			agendaTeacher
					.add(new Pair<Long, String>(currDateStart, teacherName));
		}

		return false;
	}

	/**
	 * Check for overlap in a student's agenda. The student agenda is declared
	 * by a traject. A traject contains of a group of courses which may not
	 * overlap.
	 * 
	 * @param trajectList
	 *            The list of trajects.
	 * @param entryList
	 *            The list of entries.
	 * @return true if there is overlap in the student agenda. False otherwise.
	 */
	private boolean checkForOverlapStudentAgenda(Set<Traject> trajectList,
			List<Entry> entryList) {
		for (Traject t : trajectList) {
			List<Long> studentAgenda = new ArrayList<Long>();
			for (Entry e : entryList) {
				if (t.getCourses().contains(e.getCourseComponent().getCourse())) {
					Long currDateStart = (Long) e.getStartDate().getTime();
					Long currDateEnd = (Long) Entry.calcEndDate(e).getTime();

					for (Long other : studentAgenda) {
						if (currDateStart <= other && currDateEnd > other) {
							return true;
						}
					}
					studentAgenda.add(currDateStart);
				}
			}
		}
		return false;
	}

	/**
	 * Checks that a course starts after the specified start date.
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if all courses start after the specified start date. False
	 *         otherwise.
	 */
	private boolean checkForValidStartDate(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getStartingDate()
					.compareTo(e.getStartingDate()) > 0)
				return false;
		}
		return true;
	}

	/**
	 * Checks that a course ends before the specified end date.
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if all courses end before the specified end date. False
	 *         otherwise.
	 */
	private boolean checkForValidEndDate(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getEndingDate()
					.compareTo(e.getStartingDate()) < 0)
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if all lectures are given in the room with a correct
	 *         RoomType. False otherwise.
	 */
	private boolean checkRoomType(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getRoomTypeRequirement() != e.getRoom()
					.getType()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param entryList
	 *            The list of entries
	 * @return True if all lectures are given in a room with a projector if
	 *         required. False otherwise.
	 */
	private boolean checkRoomEquipmentProjector(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getRoomProjectorRequirement() == true
					&& e.getRoom().isProjectorEquipped() == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if all lectures are given in a room with a recorder if
	 *         required. False otherwise.
	 */
	private boolean checkRoomEquipmentRecorder(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getRoomRecorderRequirement() == true
					&& e.getRoom().isRecorderEquipped() == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if all lectures are given in a room with a smartboard if
	 *         required. False otherwise.
	 */
	private boolean checkRoomEquipmentSMARTBoard(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getRoomSmartBoardRequirement() == true
					&& e.getRoom().isSmartBoardEquipped() == false) {
				return false;
			}
		}
		return true;
	}

	private boolean checkRoomsEnoughCapacity(Schedular solution) {
		for (Entry e : solution.getEntryList()) {
			int roomCapacity = e.getRoom().getCapacity();
			int numberOfStudents = e.getCourseComponent()
					.getRoomCapacityRequirement();
			if (roomCapacity < numberOfStudents) {
				return false;
			}
		}
		return true;
	}

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
	private void logEntries(String description, List<Entry> entryList) {
		Collections.sort(entryList);
		logger.info("Unit test: " + description);
		for (Entry e : entryList) {
			logger.info(e.toString());
		}
	}
}
