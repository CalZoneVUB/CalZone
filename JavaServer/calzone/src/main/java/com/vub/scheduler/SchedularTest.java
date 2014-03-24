package com.vub.scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.vub.model.Course;
import com.vub.model.Room;
import com.vub.model.Room.RoomType;

/**
 * Unit test class for the schedular.
 * 
 * @author pieter
 *
 */
public class SchedularTest {
	@Test
	public void test() {
		// StartDateList
		List<Date> startDateList = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 3, 24, 8, 0);
		startDateList.add(cal.getTime());
		cal.set(2014, 3, 24, 10, 0);
		startDateList.add(cal.getTime());
		cal.set(2014, 3, 24, 13, 0);
		startDateList.add(cal.getTime());		
		cal.set(2014, 3, 24, 15, 0);
		startDateList.add(cal.getTime());
		
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
	}
}
