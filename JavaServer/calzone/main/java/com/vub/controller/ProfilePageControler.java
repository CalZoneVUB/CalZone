package com.vub.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.exception.UserNotFoundException;
import com.vub.model.User;
import com.vub.service.UserService;

@Controller
public class ProfilePageControler {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewProfile(ModelMap model, Principal principal) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		
		String username = principal.getName();
		try {
			User user = userService.findUserByUsername(username);
			model.addAttribute("user", user);
		} catch (UserNotFoundException e) {
			// This shouldn't really happen. The principal shouldn't contain a user that isn't valid
		} finally {
			context.close();
		}
		return "profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String editProfile(Model model, @Valid User user, BindingResult result) {
		// TODO - Fix
		/*if (!result.hasErrors()) {
			UserDao userDao = new UserDao();
			userDao.updateUser(user);

			System.out.println("ProfilePageController --> " + user);
			logger.info("Updating user: " + user.toString());
			return "profile";
		} else {
			return "profile";
		}*/
		return "profile";
	}
}
