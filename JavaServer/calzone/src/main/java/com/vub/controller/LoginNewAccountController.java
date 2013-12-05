package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.Credentials;
import com.vub.model.User;

@Controller 
public class LoginNewAccountController {
	
	@RequestMapping(value = "/login/create" , method = RequestMethod.GET)
	public String showLoginCreate(@ModelAttribute("user") User user) {
		System.out.println("loginCreateAccount GET");
		return "loginCreateAccount";
	}
	
	@RequestMapping(value = "/login/create" , method = RequestMethod.POST)
	public String processLoginCreate(@ModelAttribute("user") User user) {
		System.out.println("loginCreateAccount POST");
		System.out.println("Asking to create new user with credentials: " + user);
		return "redirect:user.html";
	}
}
