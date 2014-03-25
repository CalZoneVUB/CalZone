package com.vub.controller;

//import javax.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.exception.KeyNotFoundException;
import com.vub.model.User;
import com.vub.service.KeyService;
import com.vub.service.UserService;

@Controller
public class ActivateAccountController {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/activate/{keyString}", method = RequestMethod.GET)
	public String activateUser(@PathVariable String keyString) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		KeyService keyService = (KeyService) context.getBean("keyService");
		UserService userService = (UserService) context.getBean("userService");
		
		User user;
		try {
			user = keyService.findUserByKey(keyString);
		} catch (KeyNotFoundException ex) {
			return "ActivatedNotAccount";
		} finally {
			// Close the application context in every case
			context.close();
		}
		// Activate the in-memory user
		userService.activateUser(user);
		// Delete the key from the database
		keyService.deleteKey(keyString);
		// Finally, update the user in the database
		userService.updateUser(user);
		
		logger.info("Acticated user with ID \"{}\", First name: \"{}\", Last name: \"{}\" and username: \"{}\"",
				user.getId(), user.getPerson().getFirstName(), user.getPerson().getLastName(), user.getUserName());
		return "ActivatedAccount";
	}
}