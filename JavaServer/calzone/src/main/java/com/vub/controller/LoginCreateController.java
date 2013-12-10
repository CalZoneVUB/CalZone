package com.vub.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import com.vub.model.Credentials;
import com.vub.model.User;
import com.vub.model.UserDao;

//@RequestMapping("/login")
@Controller
public class LoginCreateController {

	@RequestMapping(value = "/login/create", method = RequestMethod.GET)
	public String showLoginCreate(Model model) {
		System.out.println("loginCreateAccount GET");
		model.addAttribute("user", new User());
		return "loginCreateAccount";
	}

	@RequestMapping(value = "/login/create", method = RequestMethod.POST)
	public String processLoginCreate(Model model, @Valid User user,
			BindingResult result) {
		UserDao userDao = new UserDao();

		if (result.hasErrors()) {
			System.out.println("Form does not validate");
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				System.out.println(error);
			}
			return "loginCreateAccount";
		} 
		else if (false == userDao.checkIfUserNameAvailable(user.getUserName())) { //Username already exist
			return "loginCreateAccount";
		} 
		else if (null != userDao.findByEmail(user.getEmail())) { //Email already exists
			System.out.println("User found with email: " + user.getEmail());
			return "loginCreateAccount";
		} 
		else {
			System.out.println("Creating Unregistered User");
			userDao.insertNotRegisteredUser(user);
			// TODO Now already upgraded to registered user
			//System.out.println("Activating /Unregistered User");
			userDao.upgradeNotRegisteredUser(user);
			return "redirect:/profile/" + user.getUserName();
		}
	}

	@RequestMapping(value = "/login/successful")
	public String succesfullLogin() {
		System.out.println("/login/successful");
		return "successfulLogin";
	}

	@RequestMapping(value = "/login/create/successful/{name}")
	public String succesfullCreationName(@PathVariable String name) {
		System.out.println("/login/create/successful");
		System.out.println(name);
		return "successfulCreationAccount";
	}

	@RequestMapping(value = "/login/create/successful")
	public String succesfullCreation() {
		System.out.println("/login/create/successful");
		return "succesfullCreationAccount";
	}
}
