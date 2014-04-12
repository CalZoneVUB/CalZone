package com.vub.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.Course;
import com.vub.model.JsonResponse;
import com.vub.model.Traject;
import com.vub.service.CourseService;
import com.vub.service.TrajectService;

@Controller
public class ApiTraject {
	@RequestMapping(value="/api/traject/new", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse testPost(@RequestBody String string) {		
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	logger.info(string);
	
	//Opening courseService
	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	CourseService courseService = (CourseService) context.getBean("courseService");
	TrajectService trajectService = (TrajectService) context.getBean("trajectService");
	
	JsonResponse json = new JsonResponse();
	
	try {
	//Extracting all values form the string format key=value&key=value
	ArrayList<String> arrayList2 = new ArrayList<>();
	String[] parts = string.split("&");
	for (int i=0;i<parts.length;i++) {
		int index = parts[i].lastIndexOf("=");
		arrayList2.add(parts[i].substring(index+1));
	}
	
	logger.info(arrayList2.toString());
	
	//Ceating traject object
	Traject traject = new Traject();
	traject.setTrajectName(arrayList2.get(0).replace("+", " "));
	traject.setStartingYear(Integer.parseInt(arrayList2.get(1)));
	
	//Getting courses associated with the id form the request
	List<Course> listCourses = new ArrayList<Course>();
	for (int i=2;i<arrayList2.size();i++) {
		Course course = new Course();
		course = courseService.findCourseById(Integer.parseInt(arrayList2.get(i)));
		listCourses.add(course);
	}
	
	//Adding trajecty to the database
	traject.setCourses(listCourses);
	trajectService.createTraject(traject);
	
	//Returning positive message to front-end
    json.setStatus("success");
    json.setMessage("All went good");
    
	} catch (Exception e){
		//something went worng and returning the error to the front-end
		json.setStatus("error");
		json.setMessage("Something went worng: "+ e);
		logger.debug(e.toString());
	} finally {
		context.close();	
	}
	
	return json;
	}
}