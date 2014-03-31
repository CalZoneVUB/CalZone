package com.vub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.exception.CannotActivateUserException;
import com.vub.exception.KeyNotFoundException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Key;
import com.vub.model.User;
import com.vub.model.UserRole;
import com.vub.service.KeyService;
import com.vub.service.UserService;

@Controller
public class ActivateAccountController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/activate/{keyString}", method = RequestMethod.GET)
	public String activateUser(@PathVariable String keyString) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		KeyService keyService = (KeyService) context.getBean("keyService");
		UserService userService = (UserService) context.getBean("userService");

		User user;
		try {
			
			user = keyService.findUserByKey(keyString);
			Key key = keyService.findKey(keyString);
			// Activate the in-memory user
			userService.activateUser(user, key);
			
			// Assign ROLE_STUDENT to the user
			userService.assignUserRole(user, UserRole.UserRoleEnum.ROLE_STUDENT);
			// Update the user in the database
			userService.updateUser(user);
			// Finally, delete the key from the database
			keyService.deleteKey(keyString);
			
			logger.info("Acticated user with ID \"{}\", First name: \"{}\", Last name: \"{}\" and username: \"{}\"",
					user.getId(), user.getPerson().getFirstName(), user.getPerson().getLastName(), user.getUsername());
			
		} catch (KeyNotFoundException ex) {
			return "ActivatedNotAccount";
		} catch (UserNotFoundException ex) {
			return "ActivatedNotAccount";
		} catch (CannotActivateUserException e) {
			return "ActivatedNotAccount";
		} finally {
			// Close the application context!
			context.close();
		}
		return "ActivatedAccount";
	}
}