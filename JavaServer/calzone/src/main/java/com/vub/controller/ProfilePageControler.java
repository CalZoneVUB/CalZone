package com.vub.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.User;
import com.vub.model.UserDao;

@Controller
public class ProfilePageControler {

	@RequestMapping(value= "/profile", method = RequestMethod.GET)
	public String viewProfil(ModelMap model, Principal principal) {

		String username = principal.getName();
		UserDao userDao = new UserDao();
		User user = userDao.findByUserName(username);
		model.addAttribute("user", user);
		return "profile";
	}
}
