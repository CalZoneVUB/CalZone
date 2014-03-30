package com.vub.validators;

//import java.util.regex.Matcher;  
//import java.util.regex.Pattern;  

import javax.validation.ConstraintValidator;  
import javax.validation.ConstraintValidatorContext;  

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.dao.UserDao;
import com.vub.exception.UserNotFoundException;
import com.vub.model.User;
import com.vub.service.UserService;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {  
	
	public void initialize(ValidEmail validEmail) {    
	}  
	
	public boolean isValid(String email, ConstraintValidatorContext context) {  
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) appContext.getBean("userService");
		User u;
		try {
			u = userService.findUserByEmail(email);
		} catch (UserNotFoundException ex) {
			return false;
		} finally {
			appContext.close();
		}
		return true;
	}
}
