package com.vub.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.exception.CourseComponentNotFoundException;
import com.vub.exception.CourseNotFoundException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.JsonResponse;
import com.vub.model.User;
import com.vub.service.CourseComponentService;
import com.vub.service.CourseService;
import com.vub.service.UserService;

//@RequestMapping("/EnrollCourses")
@Controller
public class EnrollCoursesController {

	// Serving Enroll Courses Page
	@RequestMapping(value = "/EnrollCourses", method = RequestMethod.GET)
	public String enrollCoursesPage(ModelMap model) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		CourseComponentService courseComponentService = (CourseComponentService) context.getBean("courseComponentService");

		Set<Course> enrollmentSet = courseService.getCoursesInitialized();
		
		model.addAttribute("enrollmentArrayList", enrollmentSet);

		context.close();
		return "EnrollCourses";
	}

	@RequestMapping(value = "/EnrollCourses/add/{courseId}", method = RequestMethod.GET) 
	@ResponseBody
	public JsonResponse addCourse(Model model, @PathVariable int courseId, Principal principal) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		CourseService courseService = (CourseService) context.getBean("courseService");
		JsonResponse jsonResponse = new JsonResponse();
		
		try {
			User user = userService.findUserByUsername(principal.getName());
			user = userService.findUserByIdInitialized(user.getId());
			Course course = courseService.findCourseByIdInitializedEnrollements(courseId);
			
//			Set<Course> courses = user.getEnrolledCourses();
//			System.out.println("Old enrollement" + courses);
//			courses.add(course);
//			user.setEnrolledCourses(courses);
//			System.out.println(user.getEnrolledCourses());
//			userService.updateUser(user);
			
			Set<User> users = course.getEnrolledStudents();
			users.add(user);
			course.setEnrolledStudents(users);
			courseService.updateCourse(course);
			
			jsonResponse.setStatus(JsonResponse.SUCCESS);
			jsonResponse.setMessage(course.getEnrolledStudents().toString());
			
		} catch (UserNotFoundException e ) {
			jsonResponse.setStatus(JsonResponse.ERROR);
			e.printStackTrace();
		} catch (CourseNotFoundException e) {
			jsonResponse.setStatus(JsonResponse.ERROR);
			e.printStackTrace();
		}
		context.close();	
		return jsonResponse;

	}
}
