package com.vub.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.model.Traject;
import com.vub.service.TrajectService;
import com.vub.service.UserService;

@Controller 
public class HelloController {
	@RequestMapping(value = "/hello")
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello World");
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		TrajectService trajectService = (TrajectService) context.getBean("trajectService");
		
		Traject traject = trajectService.findTrajectByIdInitializedFull(3);
		System.out.println("Hallo controller: " + traject.getCourses().iterator().next().getCourseComponents());
		
		context.close();
		return "hello";
	}
}
