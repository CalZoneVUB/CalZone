package com.vub.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.Course;
import com.vub.model.Entry;
import com.vub.model.Person;
import com.vub.model.Room;
import com.vub.model.User;

//@RequestMapping("/CourseInformation")
@Controller
public class CourseInformationController {

	// Serving Enroll Courses Page
	@RequestMapping(value = "/CourseInformation", method = RequestMethod.GET)
	public String courseInformationPage(ModelMap model) {
		
		//test code: TO DELETE
		Entry testEntry = new Entry();
		Course testCourse = new Course();
		
		//testCourse.setiD(123);
		testCourse.setCourseName("JE MOEDER");
		User testUser = new User();
		Person testPerson = new Person();
		testPerson.setFirstName("Nando");
		testPerson.setLastName("Suarez Groen");
		testUser.setPerson(testPerson);
		ArrayList<User> testArray = new ArrayList<User>();
		testArray.add(testUser);
		//testCourse.setListOfAssistants(testArray);
		//testCourse.setListOfProfessors(testArray);
		//testEntry.setCourse(testCourse);
		Date testDate = new Date();
		//testEntry.setEndDate(testDate);
		testEntry.setStartingDate(testDate);
		Room testRoom = new Room();
		testRoom.setName("F.4.200");
		testEntry.setRoom(testRoom);
		model.addAttribute("testEntry", testEntry);
		//end test code
		return "CourseInformation";
	}
}
