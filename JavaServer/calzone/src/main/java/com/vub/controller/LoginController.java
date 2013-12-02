package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.Credentials;

@Controller 
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(@ModelAttribute("credentials") Credentials credentials) {
	
		System.out.println("Username: " + credentials.getUsername());
		System.out.println("Paswoord: " + credentials.getPassword());
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(@ModelAttribute("credentials") Credentials credentials) {
		System.out.println("Post Request");
		if (credentials.getUsername() != "" && credentials.getPassword() != "") {
			System.out.println(credentials.getUsername());
			System.out.println(credentials.getPassword());
			System.out.println("Login succesfull");
			return "redirect:user.html";
		} else {
			System.out.println("Login failure");
			return "redirect:login.html";
		}
	}
}
