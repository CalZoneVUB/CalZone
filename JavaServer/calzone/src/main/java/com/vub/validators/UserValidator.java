package com.vub.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.model.User;
import com.vub.service.UserService;

public class UserValidator implements ConstraintValidator<ValidUserName, String> {  

	public void initialize(ValidUserName validUserName) {    
	}  

	public boolean isValid(String userName, ConstraintValidatorContext context) {   
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) appContext.getBean("userService");
		User u = userService.findUserByUsername(userName);
		appContext.close();
		return (u != null) ? true : false;
	}  
}
