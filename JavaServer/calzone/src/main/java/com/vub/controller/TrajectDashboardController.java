package com.vub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.CourseComponent.CourseComponentTerm;
import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.User;
import com.vub.service.CourseService;

//@RequestMapping("/CourseInformation")
@Controller
public class TrajectDashboardController {

	// Serving Enroll Courses Page
	@RequestMapping(value = "/trajectdashboard", method = RequestMethod.GET)
	public String trajectDachbaord(ModelMap model) {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");

		//TODO add all new trajects
		model.addAttribute("trajectList", null);
		
		context.close();
		return "TrajectDashboardTable";
	}
	
	@RequestMapping(value = "/trajectdashboard/new", method = RequestMethod.GET)
	public String trajectDachbaordNew(ModelMap model) {
		
		return "TrajectAddDashboardTable";
	}
}
