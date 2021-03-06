package com.vub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.Traject;
import com.vub.service.TrajectService;

//@RequestMapping("/CourseInformation")
@Controller
public class SchedularDashboardController {

	// Serving Enroll Courses Page
	@RequestMapping(value = "/schedulardashboard", method = RequestMethod.GET)
	public String schedularDachbaord(ModelMap model) {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TrajectService trajectService = (TrajectService) context.getBean("trajectService");
		//Adding the list of all traject to the model to display 
		Set<Traject> setTrajects = trajectService.getTrajects();
		List<Traject> listTrajects = new ArrayList<Traject>(setTrajects);
		
		//TODO change to real not fronzen list
		model.addAttribute("listTrajectsNotFrozen", listTrajects);
		model.addAttribute("listTrajects" , listTrajects);
		
		context.close();
		return "SchedularDashboard";
	}
}
