package com.vub.scheduler;

import java.util.Date;
import java.util.HashSet;

import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.User;
import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.model.Room.RoomType;

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
	 * @param teacher
	 *            The teacher of the course.
	 * @return A new CourseComponent object.
	 * 
	 * @author pieter
	 */
	public static CourseComponent createCourseComponent(User teacher) {
		return createCourseComponent(teacher, 20, 2, 2,
				CourseComponentType.HOC, false, false, false,
				RoomType.ClassRoom);
	}

	public static CourseComponent createCourseComponent(User teacher,
			int numberOfStudents, int contactHours, int duration,
			CourseComponentType cc) {
		return createCourseComponent(teacher, numberOfStudents, contactHours,
				duration, cc, false, false, false, RoomType.ClassRoom);
	}

	/**
	 * Creates a new CourseComponent object (connected with a course object)
	 * with initialized parameters.
	 * 
	 * @param teacher
	 *            The specified teacher.
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
	public static CourseComponent createCourseComponent(User teacher,
			int numberOfStudents, int contactHours, int duration,
			CourseComponentType ccType, boolean roomProjectorRequirement,
			boolean roomRecorderRequirement, boolean roomSMARTBoardRequirement,
			RoomType roomType) {
		HashSet<User> teachers1 = new HashSet<User>();
		teachers1.add(teacher);

		Course course1 = new Course();

		HashSet<CourseComponent> courseComponents1 = new HashSet<CourseComponent>();
		CourseComponent cc = new CourseComponent();
		cc.setTeachers(teachers1);
		cc.setCourse(course1);
		cc.setRoomCapacityRequirement(numberOfStudents);
		cc.setContactHours(contactHours);
		cc.setDuration(duration);
		cc.setType(ccType);
		cc.setRoomProjectorRequirement(roomProjectorRequirement);
		cc.setRoomRecorderRequirement(roomRecorderRequirement);
		cc.setRoomSmartBoardRequirement(roomSMARTBoardRequirement);
		cc.setRoomTypeRequirement(roomType);
		courseComponents1.add(cc);
		cc.setStartingDate(new Date(2013, 1, 1));
		course1.setCourseComponents(courseComponents1);

		return cc;
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

		Entry e = new Entry();
		e.setStartDate(startDate);
		e.setCourseComponent(createCourseComponent(teacher, 20, duration,
				duration, CourseComponentType.HOC));
		e.setRoom(createRoom());

		return e;
	}
}
