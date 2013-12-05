package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.Credentials;
import com.vub.model.User;

@Controller 
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showLogin(@ModelAttribute("credentials") Credentials credentials) {
		
		System.out.println("/login GET");
		//System.out.println("Username: " + credentials.getUsername());
		//System.out.println("Paswoord: " + credentials.getPassword());
		return "login";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String processLogin(@ModelAttribute("credentials") Credentials credentials) {
		System.out.println("/login POST");
		if (credentials.getUsername() != "" && credentials.getPassword() != "") {
			System.out.println(credentials.getUsername());
			System.out.println(credentials.getPassword());
			System.out.println("Login succesfull");
			return "redirect:login/succesfull";
		} else {
			System.out.println("Login failure");
			return "redirect:login/create";
		}
	}
	
}
