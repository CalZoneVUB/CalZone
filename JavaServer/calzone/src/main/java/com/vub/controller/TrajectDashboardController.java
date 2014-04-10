package com.vub.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
