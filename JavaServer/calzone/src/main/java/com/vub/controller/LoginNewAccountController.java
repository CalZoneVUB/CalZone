package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.User;

@Controller
public class LoginNewAccountController {

	@RequestMapping(value = "/login/create", method = RequestMethod.GET)
	public String showLoginCreate(@ModelAttribute("user") User user) {
		System.out.println("loginCreateAccount GET");
		return "loginCreateAccount";
	}

	@RequestMapping(value = "/login/create", method = RequestMethod.POST)
	public String processLoginCreate(@ModelAttribute("user") User user) {
		System.out.println("loginCreateAccount POST");
		System.out.println("Asking to create new user with credentials: "
				+ user);
		return "redirect:/login/create/succesfull/" + user.getName();
	}

	@RequestMapping("/login/succesfull")
	public String succesfullLogin() {
		System.out.println("/login/succesfull");
		return "succesfullLogin";
	}

	@RequestMapping("/login/create/succesfull")
	public String succesfullCreation() {
		System.out.println("/login/create/succesfull");
		return "succesfullCreationAccount";
	}
}
