package com.vub.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.exception.CourseNotFoundException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Course;
import com.vub.model.JsonResponse;
import com.vub.model.User;
import com.vub.service.CourseService;
import com.vub.service.UserService;


@Controller
public class EnrolledCoursesController {

	@Autowired
	UserService userService;
	
	@Autowired
	CourseService courseService;
	
	// Serving Enrolled Courses Page
	@RequestMapping(value = "/EnrolledCourses", method = RequestMethod.GET)
	public String enrolledCoursesPage(ModelMap model, Principal principal) {
	
		try {
			User user = userService.findUserByUsername(principal.getName());
			user = userService.findUserByIdInitialized(user.getId());
			Set<Course> enrollmentSet = user.getEnrolledCourses();
			System.out.println("UserEnrollements" + user.getEnrolledCourses());
			model.addAttribute("enrollmentArrayList", enrollmentSet);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		return "EnrolledCourses";
	}

	@RequestMapping(value = "/EnrolledCourses/remove/{courseId}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse removeCourse(Model model, @PathVariable int courseId, Principal principal) {

		JsonResponse jsonResponse = new JsonResponse();
		try {
			User user = userService.findUserByUsername(principal.getName());
			Course course = courseService.findCourseByIdInitializedEnrollements(courseId);
			Set<User> users = course.getEnrolledStudents();
			System.out.println(users.remove(user));
			course.setEnrolledStudents(users);
			courseService.updateCourse(course);

		} catch (UserNotFoundException e) {
			jsonResponse.setStatus(JsonResponse.ERROR);
			e.printStackTrace();
			return jsonResponse;
			
		} catch (CourseNotFoundException e) {
			// TODO Auto-generated catch block
			jsonResponse.setStatus(JsonResponse.ERROR);
			e.printStackTrace();
			return jsonResponse;
		}

		jsonResponse.setStatus(JsonResponse.SUCCESS);
		return jsonResponse;
	}



}
