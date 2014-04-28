package com.vub.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.exception.UserNotFoundException;
import com.vub.model.Course;
import com.vub.model.User;
import com.vub.service.UserService;

//@RequestMapping("/EnrolledCourses")
@Controller
public class EnrolledCoursesController {

	// Serving Enrolled Courses Page
	@RequestMapping(value = "/EnrolledCourses", method = RequestMethod.GET)
	public String enrolledCoursesPage(ModelMap model, Principal principal) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");

		try {
			User user = userService.findUserByUsername(principal.getName());
			user = userService.findUserByIdInitialized(user.getId());
			Set<Course> enrollmentSet = user.getEnrolledCourses();
			System.out.println("UserEnrollements" + user.getEnrolledCourses());
			model.addAttribute("enrollmentArrayList", enrollmentSet);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		context.close();
		return "EnrolledCourses";
	}

	@RequestMapping(value = "/EnrolledCourses/remove/{courseId}", method = RequestMethod.GET)
	public String removeCourse(Model model, @PathVariable String courseId, Principal principal) {

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");

		try {
			User user = userService.findUserByUsername(principal.getName());
			user = userService.findUserByIdInitialized(user.getId());
			Set<Course> enrollmentSet = user.getEnrolledCourses();
			Course c = new Course();
			for (Course course : enrollmentSet) {
				if (Integer.toString(course.getId()) == courseId) {
					c = course;
				}
			}
			if (c != null) {
				enrollmentSet.remove(c);
				user.setEnrolledCourses(enrollmentSet);
				userService.updateUser(user);
			}

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		context.close();

		return "redirect:/EnrolledCourses";
	}



}
