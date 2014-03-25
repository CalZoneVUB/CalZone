package com.vub.controller;

//import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.model.Globals;
import com.vub.model.Key;
import com.vub.model.MailMail;
import com.vub.model.User;
import com.vub.service.KeyService;
import com.vub.service.MailService;
import com.vub.service.UserService;

@Controller
public class LoginCreateController {
	
	@RequestMapping(value = "/login/create", method = RequestMethod.GET)
	public String showLoginCreate(Model model) {
		model.addAttribute("user", new User());
		return "loginCreateAccount";
	}

	@RequestMapping(value = "/login/create", method = RequestMethod.POST)
	public String processLoginCreate(Model model, @Valid User user, BindingResult result) {
		
		if (result.hasErrors()) { // Errors in one of the required fields
			List<ObjectError> errors = result.getAllErrors();
			//TODO - Update to use log
			for (ObjectError error : errors) {
				if (Globals.DEBUG == 1) 
					System.out.println(error);
			}
			return "loginCreateAccount";
		} else {
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			UserService userService = (UserService) context.getBean("userService");
			KeyService keyService = (KeyService) context.getBean("keyService");
			MailService mailService = (MailService) context.getBean("mailService");

			if (Globals.DEBUG == 1) {
				System.out.println("New Password is: " + user.getPassword());
			}
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
			
			return "ActivateYourAccount";
		}
	}

	@RequestMapping(value = "/login/successful")
	public String succesfullLogin() {
		if (Globals.DEBUG == 1) 
			System.out.println("/login/successful");
		return "successfulLogin";
	}

	@RequestMapping(value = "/login/create/successful/{name}")
	public String succesfullCreationName(@PathVariable String name) {
		if (Globals.DEBUG == 1) {
			System.out.println("/login/create/successful");
			System.out.println(name);
		}
		return "successfulCreationAccount";
	}

	@RequestMapping(value = "/login/create/successful")
	public String succesfullCreation() {
		if (Globals.DEBUG == 1) 
			System.out.println("/login/create/successful");
		return "succesfullCreationAccount";
	}
}
