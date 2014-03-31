package com.vub.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.exception.UserNotFoundException;
import com.vub.model.User;
import com.vub.service.UserService;

public class UserValidator implements ConstraintValidator<ValidUserName, String> {  

	public void initialize(ValidUserName validUserName) {    
	}  

	public boolean isValid(String userName, ConstraintValidatorContext context) {   
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) appContext.getBean("userService");
		User u;
		try {
			u = userService.findUserByUsername(userName);
		} catch (UserNotFoundException ex) {
			// When the user has not been found, return true, because it means the username isn't already in the system
			return true;
		} finally {
			appContext.close();
		}
		// If the user is found, return false, because the user has been found (thus username is invalid)
		return false;
	}  
}
