package com.vub.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.model.Room;
import com.vub.service.RoomService;
import com.vub.validators.ClassroomValidator;

@Controller 
public class AdminDashboard {
	
	@RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
	public String mainPage(Model model) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//RoomService roomService = (RoomService) context.getBean("roomService");
		
		
		//model.addAttribute("roomTypes", Room.RoomType.values());
		context.close();
		return "AdminDashboard"; 
	}
	
	@RequestMapping(value = "/admindashbaord" , method = RequestMethod.POST)
	public String processSumit(Model model, @ModelAttribute("room") Room room, BindingResult result) {
		ClassroomValidator validator = new ClassroomValidator();
		validator.validate(room, result);
		
		if (result.hasErrors()) // Errors in one of the required fields
			return "Classrooms";
		else {
			return "redirect:/classrooms";
		}
	}
}