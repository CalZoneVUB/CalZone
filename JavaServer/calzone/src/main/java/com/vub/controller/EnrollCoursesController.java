package com.vub.controller;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.dao.CourseDao;
import com.vub.model.Course;

//@RequestMapping("/EnrollCourses")
@Controller
public class EnrollCoursesController {

	// Serving Enroll Courses Page
	@RequestMapping(value = "/EnrollCourses", method = RequestMethod.GET)
	public String enrollCoursesPage(ModelMap model) {
		ArrayList<Course> courseArrayList = new ArrayList<Course>();
		CourseDao courseDao = new CourseDao();
		courseArrayList = courseDao.getCourses();
		courseDao.closeDao();
		Collections.sort(courseArrayList);
		model.addAttribute("courseArrayList", courseArrayList);
		return "EnrollCourses";
	}

}
