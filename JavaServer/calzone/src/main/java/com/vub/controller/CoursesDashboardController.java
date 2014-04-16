package com.vub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.exception.CourseNotFoundException;
import com.vub.model.Course;
import com.vub.service.CourseService;

//@RequestMapping("/CourseInformation")
@Controller
public class CoursesDashboardController {

	/**
	 * @param model : model of /coursesdashbaord
	 * @return : returns coursedashbaordtable.jsp with coureList
	 */
	// Serving Enroll Courses Page
	@RequestMapping(value = "/coursesdashboard", method = RequestMethod.GET)
	public String courseDachbaord(ModelMap model) {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		
		Set<Course> courseSet = courseService.getCourses();
		List<Course> courseList = new ArrayList<Course>(courseSet);
		
		model.addAttribute("courseList", courseList);
		
		return "CourseDashboardTable";
	}
	
	/**
	 * @param model : model for spring
	 * @param id : id of the course object in the database to retreive
	 * @return : terurns the CourseEditDashboard.jsp
	 */
	@RequestMapping(value = "/coursesdashboard/edit/{id}", method = RequestMethod.GET)
	public String courseDachbaordEdit(ModelMap model , @PathVariable int id) {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		
		Course course = new Course();
		try {
			course = courseService.findCourseByIdInitialized(id); //All object needed to be loaded in the course 
			System.out.println("This course is fetched:" + course);
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
		}
		model.addAttribute("course", course);
		context.close();
		return "CourseEditDashboard";
	}
}
