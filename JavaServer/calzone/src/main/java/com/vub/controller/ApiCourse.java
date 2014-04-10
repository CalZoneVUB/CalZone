package com.vub.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.Course;
import com.vub.model.SelectResponseConverter;
import com.vub.service.CourseService;

/**
 * @author Tim
 * API returning information recarding courses
 */
@Controller
public class ApiCourse {

	/**
	 * @return returns list  of courses in foramte (courseId, courseName)
	 */
	@RequestMapping(value="/api/course/all/formated", method = RequestMethod.GET)
    @ResponseBody
    public List<SelectResponse> testPost() {		
	final Logger logger = LoggerFactory.getLogger(this.getClass());
   
	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	CourseService courseService = (CourseService) context.getBean("courseService");
	List<Course> courseArray = courseService.getCourses();
	SelectResponseConverter converter = new SelectResponseConverter();
	List<SelectResponse> listSelectResponses = converter.classesToSelectResponse(courseArray);
	//List<SelectResponse> list = new ArrayList<SelectResponse>();
	//list.add(new SelectResponse(3,"Test 1"));
	//list.add(new SelectResponse(4,"Test 2"));
	System.out.println(listSelectResponses);
	context.close();
	return listSelectResponses;
	}
}