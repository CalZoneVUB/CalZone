package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping("/EnrolledCourses")
@Controller
public class EnrolledCoursesController {

	// Serving Enrolled Courses Page
	@RequestMapping(value = "/EnrolledCourses", method = RequestMethod.GET)
	public String enrolledCoursesPage(ModelMap model) {
		return "EnrolledCourses";
	}

}
