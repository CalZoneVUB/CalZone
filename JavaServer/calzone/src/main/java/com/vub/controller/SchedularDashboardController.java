package com.vub.controller;

import java.util.List;
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
		List<Traject> listTrajects = trajectService.getTrajects();
		
		//TODO Remove dummy's
		Traject t1 = new Traject();
		t1.setTrajectName("Traject Number 1");
		listTrajects.add(t1);
		Traject t2 = new Traject();
		t1.setTrajectName("Traject Number 2");
		listTrajects.add(t2);
			
		model.addAttribute("listTrajects" , listTrajects);
		return "SchedularDashboard";
	}
}
