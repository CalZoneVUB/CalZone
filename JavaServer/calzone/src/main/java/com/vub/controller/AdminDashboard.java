package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.vub.model.Room;
import com.vub.validators.ClassroomValidator;

/**
 * 
 * @author Tim
 *
 */
@Controller 
public class AdminDashboard {
	
	@RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
	public String mainPage(Model model) {

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