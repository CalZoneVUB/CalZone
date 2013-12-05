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
public class PasswordForgetController {
	
	@RequestMapping(value = "/passwordforgot", method = RequestMethod.GET)
	public String processLogin() {
		System.out.println("/password POST");
		return "password"; 
	}
}
