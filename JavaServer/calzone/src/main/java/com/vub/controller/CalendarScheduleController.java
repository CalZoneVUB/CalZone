package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Controller 
public class CalendarScheduleController {
	
	@RequestMapping(value = "/calendarschedule", method = RequestMethod.GET)
	public String calendarSchedularPage(Model model) {

		return "CalendarSchedular";
	}
}