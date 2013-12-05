package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.vub.model.Credentials;
import com.vub.model.User;

 
@Controller
public class ProfilePageControler {
	
	@RequestMapping(value = "/profile/{userName}", method = RequestMethod.GET)
	public String processLogin(@ModelAttribute("user") User user, @PathVariable String userName) {
		System.out.println("/profile POST");
		System.out.println("Showing profile of" + user);
		return "redirect:/"; //redirect to main page
	}
}
