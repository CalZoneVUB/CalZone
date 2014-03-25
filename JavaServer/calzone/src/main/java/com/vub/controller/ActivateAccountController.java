package com.vub.controller;

//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.exception.KeyNotFoundException;
import com.vub.model.Globals;
import com.vub.model.User;
import com.vub.service.KeyService;
import com.vub.service.UserService;

@Controller
public class ActivateAccountController {

	@RequestMapping(value = "/activate/{keyString}", method = RequestMethod.GET)
	public String activateUser(@PathVariable String keyString) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		KeyService keyService = (KeyService) context.getBean("keyService");
		UserService userService = (UserService) context.getBean("userService");
		
		if (Globals.DEBUG == 1) 
			System.out.println("Activation user contoller");
		
		User user;
		try {
			user = keyService.findUserByKey(keyString);
		} catch (KeyNotFoundException ex) {
			return "ActivatedNotAccount";
		}
		// Activate the in-memory user
		userService.activateUser(user);
		// Delete the key from the database
		keyService.deleteKey(keyString);
		// Finally, update the user in the database
		userService.updateUser(user);
		// Close the applicationcontext
		context.close();
		
		return "ActivatedAccount";
	}
}