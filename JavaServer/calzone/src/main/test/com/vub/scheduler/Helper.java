package com.vub.scheduler;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.Room.RoomType;
import com.vub.model.Traject;
import com.vub.model.User;
import com.vub.utility.DateUtility;

public class Helper {
	/**
	 * Creates a default Room object with parameters:
	 * <ul>
	 * <li>Capacity is 40
	 * <li>Roomtype is ClassRoom
	 * <li>ProjectorEquipped is false
	 * </ul>
	 * 
	 * @return A new room object,
	 */
	public static Room createRoom() {
		return createRoom(40, false, false, false, RoomType.ClassRoom);
	}

	public static Room createRoom(int capacity) {
		return createRoom(capacity, false, false, false, RoomType.ClassRoom);
	}

	/**
	 * Creates a default Room object with parameters:
	 * <ul>
	 * <li>Capacity is specified as parameter
	 * <li>Roomtype is ClassRoom
	 * <li>ProjectorEquipped is false
	 * </ul>
	 * 
	 * @param capacity
	 *            The number of seats of the room.
	 * @param hasProjector
	 * @param hasRecorder
	 * @param hasSmartBoard
	 * @param roomType
	 * @return A new room object,
	 */
	public static Room createRoom(int capacity, boolean hasProjector,
			boolean hasRecorder, boolean hasSmartBoard, RoomType roomType) {
		Room room = new Room();
		room.setCapacity(capacity);
		room.setRecorderEquipped(hasRecorder);
		room.setProjectorEquipped(hasProjector);
		room.setSmartBoardEquipped(hasSmartBoard);
		room.setType(roomType);
		room.setId(new Random().nextInt(30000));

		return room;
	}

	/**
	 * Creates a new CourseComponent object (connected with a course object)
	 * with following default parameters:
	 * <ul>
	 * <li>20 students
	 * <li>2 contactshours
	 * <li>A lecture has a duction of 2 hours.
	 * <li>The teacher is passed as an argument.
	 * </ul>
	 * 
	 * @param teachers
	 *            The teacher of the course.
	 * @return A new CourseComponent object.
	 * 
	 * @author pieter
	 */
	public static CourseComponent createCourseComponent(HashSet<User> teachers) {
		return createCourseComponent(teachers, 20, 2, 2,
				CourseComponentType.HOC, false, false, false,
				RoomType.ClassRoom);
	}

	public static CourseComponent createCourseComponent(HashSet<User> teachers,
			int numberOfStudents, int contactHours, int duration,
			CourseComponentType cc) {
		return createCourseComponent(teachers, numberOfStudents, contactHours,
				duration, cc, false, false, false, RoomType.ClassRoom);
	}

	/**
	 * Creates a new CourseComponent object (connected with a course object)
	 * with initialized parameters.
	 * 
	 * @param teachers
	 *            The specified teachers.
	 * @param numberOfStudents
	 *            The number of students enrolled in this course.
	 * @param contactHours
	 *            The total amount of hours of this course.
	 * @param duration
	 *            The duration of one lecture.
	 * @param ccType
	 *            The course component type
	 * @param roomProjectorRequirement
	 *            Course needs projector?
	 * @param roomRecorderRequirement
	 *            Course needs recorder?
	 * @param roomSMARTBoardRequirement
	 *            Course needs smartboard?
	 * @param roomType
	 *            Normall classroom or computerroom?
	 * @return A new CourseComponent object.
	 */
	public static CourseComponent createCourseComponent(HashSet<User> teachers,
			int numberOfStudents, int contactHours, int duration,
			CourseComponentType ccType, boolean roomProjectorRequirement,
			boolean roomRecorderRequirement, boolean roomSMARTBoardRequirement,
			RoomType roomType) {
		Course course = new Course();
		course.setId(new Random().nextInt(30000));
		course.setCourseName("myCourse");

		HashSet<CourseComponent> courseComponents1 = new HashSet<CourseComponent>();
		CourseComponent cc = new CourseComponent();
		cc.setTeachers(teachers);
		cc.setCourse(course);
		cc.setRoomCapacityRequirement(numberOfStudents);
		cc.setContactHours(contactHours);
		cc.setDuration(duration);
		cc.setType(ccType);
		cc.setRoomProjectorRequirement(roomProjectorRequirement);
		cc.setRoomRecorderRequirement(roomRecorderRequirement);
		cc.setRoomSmartBoardRequirement(roomSMARTBoardRequirement);
		cc.setRoomTypeRequirement(roomType);
		cc.setId(new Random().nextInt(30000));
		courseComponents1.add(cc);
		cc.setStartingDate(DateUtility.createDate(2013, 0, 1));
		cc.setEndingDate(DateUtility.createDate(2015, 0, 1));
		cc.setPreferedDayOfWeek(2); // Set prefered day on monday
		cc.setPreferedStartHour(8); // Set prefered start hour at 8 am
		course.setCourseComponents(courseComponents1);

		return cc;
	}

	/**
	 * Creates a traject composed of exactly the given coursecomponent.
	 * 
	 * @param cc the coursecomponent.
	 * @return a list of exactly one traject.
	 */
	public static Set<Traject> createTraject(CourseComponent cc) {
		return createTraject(Arrays.asList(cc));
	}

	/**
	 * Creates a traject composed of the courses that are in the list of
	 * coursecomponents.
	 * 
	 * @param ccList
	 *            the list of course components.
	 * @return a list of exactly one traject.
	 */
	public static Set<Traject> createTraject(List<CourseComponent> ccList) {
		Set<Course> courses = new HashSet<Course>();
		for (CourseComponent cc : ccList) {
			courses.add(cc.getCourse());
		}
		
		Traject traject = new Traject();
		traject.setId(new Random().nextInt(30000));
		traject.setCourses(courses);
		
		Set<Traject> result = new HashSet<Traject>();
		result.add(traject);
		
		for (Course c : courses) {
			c.setTrajects(result);
		}

		return result;
	}

	/**
	 * Creates a new teacher relation that can be used for a course component.
	 * 
	 * @param teacherName
	 *            The name of the teacher.
	 * @return A set that contains the teacher.
	 */
	public static HashSet<User> createTeachers(String teacherName) {
		User teacher = new User();
		teacher.setUsername(teacherName);
		teacher.setId(new Random().nextInt(30000));

		HashSet<User> teachers = new HashSet<User>();
		teachers.add(teacher);
		return teachers;
	}

	/**
	 * Creates a new entry with a startdate and a duration. A room,
	 * coursecomponent and teacher are automaticaly created.
	 * 
	 * @param startDate
	 *            The startdate of the entry.
	 * @param duration
	 *            The duration of the entry.
	 * @return the entry.
	 */
	public static Entry createEntry(Date startDate, int duration) {
		// The teacher of the course
		User teacher = new User();
		teacher.setUsername("Pieter");
		HashSet<User> teachers = new HashSet<User>();
		teachers.add(teacher);

		Entry e = new Entry();
		e.setStartingDate(startDate);
		e.setCourseComponent(createCourseComponent(teachers, 20, duration,
				duration, CourseComponentType.HOC));
		e.setRoom(createRoom());

		return e;
	}
}
