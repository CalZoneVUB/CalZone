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

		Set<Course> enrollmentSet = courseService.getCourses();
		Set<Course> courses = new HashSet<Course>();
		for (Course c : enrollmentSet) {
			try {
				Course course = courseService.findCourseByIdInitialized(c.getId());
				Set<CourseComponent> components = new HashSet<CourseComponent>();
				for (CourseComponent cc : course.getCourseComponents()) {
					try {
						components.add(courseComponentService.findCourseComponentByIdInitialized(cc.getId()));
					} catch (CourseComponentNotFoundException e) {
						e.printStackTrace();
					}
				}
				course.setCourseComponents(components);
				courses.add(course);
			} catch (CourseNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute("enrollmentArrayList", courses);

		context.close();
		return "EnrolledCourses";
	}

	@RequestMapping(value = "/EnrollCourses/add/{courseId}", method = RequestMethod.GET) 
	public String addCourse(Model model, @PathVariable String courseId, Principal principal) {
		// TODO - Update met toegevoegde services
		/*User user = new UserDao().findByUserName(principal.getName());
		// TODO rekening houden met academic year
		user.addEnrolledCourse(new Enrollment(new CourseDao().findByCourseID(Integer.parseInt(courseId)), 20132014));*/
		return "redirect:/EnrolledCourses";
	}
}
