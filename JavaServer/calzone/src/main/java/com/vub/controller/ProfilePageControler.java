package com.vub.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.dao.UserDao;
import com.vub.model.Globals;
import com.vub.model.User;

@Controller
public class ProfilePageControler {

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewProfil(ModelMap model, Principal principal) {

		String username = principal.getName();
		UserDao userDao = new UserDao();
		User user = userDao.findByUserName(username);
		model.addAttribute("user", user);
		return "profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String editProfile(Model model, @Valid User user,
			BindingResult result) {
		UserDao userDao = new UserDao();
		userDao.updateUser(user);
		if (Globals.DEBUG == 1) {
			System.out.println("Updating user: ");
			System.out.println(user.toString());
		}
		return "profile";
	}
}
