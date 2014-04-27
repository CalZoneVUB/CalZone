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

import com.vub.exception.CourseComponentNotFoundException;
import com.vub.exception.CourseNotFoundException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
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

		Set<Course> enrollmentSet = courseService.getCoursesInitialized(0,20);
		
		model.addAttribute("enrollmentArrayList", enrollmentSet);

		context.close();
		return "EnrollCourses";
	}

	@RequestMapping(value = "/EnrollCourses/add/{courseId}", method = RequestMethod.GET) 
	public void addCourse(Model model, @PathVariable int courseId, Principal principal) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		CourseService courseService = (CourseService) context.getBean("courseService");
		try {
			User user = userService.findUserByUsername(principal.getName());
			user = userService.findUserByIdInitialized(user.getId());
			Course course = courseService.findCourseById(courseId);
			
			Set<Course> courses = user.getEnrolledCourses();
			System.out.println("Old enrollement" + courses);
			courses.add(course);
			user.setEnrolledCourses(courses);
			System.out.println(user);
			userService.updateUser(user);
			System.out.println("New enrollement" + courses);
			
		} catch (UserNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CourseNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.close();
		
		// TODO - Update met toegevoegde services
		/*User user = new UserDao().findByUserName(principal.getName());
		// TODO rekening houden met academic year
		user.addEnrolledCourse(new Enrollment(new CourseDao().findByCourseID(Integer.parseInt(courseId)), 20132014));*/
		//return "redirect:/EnrolledCourses";
		//return "EnrollCourses";
	}
}
