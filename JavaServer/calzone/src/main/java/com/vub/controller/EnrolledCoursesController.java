package com.vub.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.dao.CourseDao;
import com.vub.model.Course;

//@RequestMapping("/EnrolledCourses")
@Controller
public class EnrolledCoursesController {

	// Serving Enrolled Courses Page
	@RequestMapping(value = "/EnrolledCourses", method = RequestMethod.GET)
	public String enrolledCoursesPage(ModelMap model) {
		ArrayList<Course> courseArrayList = new ArrayList<Course>();
		CourseDao courseDao = new CourseDao();
		courseArrayList = courseDao.getCourses();
		courseDao.closeDao();
		System.out.println(courseArrayList);
//		// TODO - REMOVE		
//		// Populate a course, because the database does not contain any courses yet
//		Course c1 = new Course();
//		c1.setiD(1234);
//		ArrayList<User> professorArrayList1 = new ArrayList<User>();
//		//make user
//		User u1 = new User();
//		u1.setFirstName("Ragnhild");
//		u1.setLastName("Van Der Straeten");
//		//make prof from user
//		User p1 = new User(u1);
//		//add prof to arraylist
//		professorArrayList1.add(p1);
//		c1.setListOfProfessors(professorArrayList1);
//		courseArrayList.add(c1);
//	
//		Course c2 = new Course();
//		c2.setiD(5678);
//		ArrayList<User> professorArrayList2 = new ArrayList<User>();
//		//make user
//		User u2 = new User();
//		u2.setFirstName("Youri");
//		u2.setLastName("Coppens");
//		//make prof from user
//		User p2 = new User(u2);
//		//add prof to arraylist
//		professorArrayList2.add(p2);
//		c2.setListOfProfessors(professorArrayList2);
//		courseArrayList.add(c2);
//		
//		Course c3 = new Course();
//		c3.setiD(6969);
//		ArrayList<User> professorArrayList3 = new ArrayList<User>();
//		//make user
//		User u3 = new User();
//		u3.setFirstName("Nicolas");
//		u3.setLastName("Carraggi");
//		//make prof from user
//		User p3 = new User(u3);
//		//add prof to arraylist
//		professorArrayList3.add(p3);
//		c3.setListOfProfessors(professorArrayList3);
//		courseArrayList.add(c3);
		
		
		model.addAttribute("courseArrayList", courseArrayList);
		return "EnrolledCourses";
	}

}
