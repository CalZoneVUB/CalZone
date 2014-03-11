package com.vub.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.dao.CourseDao;
import com.vub.dao.UserDao;
import com.vub.model.Course;
import com.vub.model.Enrollment;
import com.vub.model.User;

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

	@RequestMapping(value = "/EnrollCourses/add/{courseId}", method = RequestMethod.POST) 
	public String addCourse(Model model, @PathVariable String courseId, Principal principal) {
		User user = new UserDao().findByUserName(principal.getName());
		ArrayList<Enrollment> listOfEnrollments = new UserDao().findByUserName(principal.getName()).getListOfEnrollments();
		// TODO rekening houden met academic year
		listOfEnrollments.add(new Enrollment(new CourseDao().findByCourseID(Integer.parseInt(courseId)), 20132014));
		user.setListOfEnrollments(listOfEnrollments);
		return "EnrolledCourses";
	}
}
