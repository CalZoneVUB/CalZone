package com.vub.controller;

//import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.Key;
import com.vub.model.User;
import com.vub.service.KeyService;
import com.vub.service.MailService;
import com.vub.service.UserService;

@Controller
public class LoginCreateController {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/login/create", method = RequestMethod.GET)
	public String showLoginCreate(Model model) {
		model.addAttribute("user", new User());
		return "loginCreateAccount";
	}

	@RequestMapping(value = "/login/create", method = RequestMethod.POST)
	public String processLoginCreate(Model model, @Valid User user, BindingResult result) {
		
		if (result.hasErrors()) { // Errors in one of the required fields
			List<ObjectError> errors = result.getAllErrors();
			
			for (ObjectError error : errors) {
				logger.error(error.toString());
			}
			
			return "loginCreateAccount";
		} else {
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			UserService userService = (UserService) context.getBean("userService");
			KeyService keyService = (KeyService) context.getBean("keyService");
			MailService mailService = (MailService) context.getBean("mailService");

			// The password has been automatically added in the user class by Spring, but we must hash it
			userService.hashPassword(user);
			// Store the user in the database
			userService.createUser(user);
			
			// Generate a new activation key
			Key activationKey = keyService.generateActivationKey(user);
			// Store the key in the database
			keyService.createKey(activationKey);
			// Send activation e-mail
			mailService.sendActivationMail(user, activationKey);
			// Close the applicationcontext
			context.close();
			
			logger.info("Created new User: {}", user.toString());
			
			return "ActivateYourAccount";
		}
	}

	@RequestMapping(value = "/login/successful")
	public String succesfullLogin() {
		// TODO - logger.info("Successful user login"); ?
		return "successfulLogin";
	}

	@RequestMapping(value = "/login/create/successful/{name}")
	public String succesfullCreationName(@PathVariable String name) {
		// TODO - logger.info("Successful account creation: {}", name); ?
		return "successfulCreationAccount";
	}

	@RequestMapping(value = "/login/create/successful")
	public String succesfullCreation() {
		// TODO - Log message
		return "succesfullCreationAccount";
	}
}
