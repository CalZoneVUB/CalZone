package com.vub.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.vub.model.Course;
import com.vub.model.JsonResponse;
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
	//final Logger logger = LoggerFactory.getLogger(this.getClass());
   
	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	CourseService courseService = (CourseService) context.getBean("courseService");
	List<Course> courseArray = courseService.getCourses();
	SelectResponseConverter converter = new SelectResponseConverter();
	List<SelectResponse> listSelectResponses = converter.classesToSelectResponse(courseArray);
	System.out.println(listSelectResponses);
	context.close();
	return listSelectResponses;
	}
	
	/**
	 * @param value : value input by the user
	 * @param name : name given by the id in javascript
	 * @param pk : primary key of the object in the database
	 * @return returns Json success or error message
	 */
	@RequestMapping(value="/api/course/edit", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse postEdit(@RequestParam(value="value") String value, @RequestParam(value="name") String name,@RequestParam(value="pk") int pk) {		
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());	
	logger.info("Received params value: " + value + " and name: " + name + "and pk: " + pk);
	
	System.out.println("Received params value: " + value + " and name: " + name + "and pk: " + pk);
    
	JsonResponse json = new JsonResponse();
    json.setMessage("Try again");
    json.setStatus("success"); //json.setStatus("error");
    
    //TODO value to corresponding edit
    
    return json;
	}
}