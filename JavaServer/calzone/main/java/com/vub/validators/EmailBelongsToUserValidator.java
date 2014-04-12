package com.vub.validators;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.vub.exception.UserNotFoundException;
import com.vub.model.Email;
import com.vub.model.User;
import com.vub.service.UserService;

/**
 * Implements the Validator interface and checks whether a given Email class is a valid e-mail address, which belongs to a registered user.
 * 
 * @author Sam Van den Vonder
 *
 */

public class EmailBelongsToUserValidator implements Validator {  
	public void validate(Object obj, Errors e) {
		String email = (String) obj;
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		
		// Check if the e-mail has a valid character sequence
		if(!isValidEmailAddress(email))
			e.rejectValue("email", "email.blankorinvalid.text");	
		
		try {
			// Suppress the warning, because it doesn't matter if we use it or not - the exception it may throw (or not) is the important bit
			@SuppressWarnings("unused")
			User user = userService.findUserByEmail(email);
		} catch (UserNotFoundException ex) {
			e.rejectValue("email", "passwordforgot.emailunavailable.text");
		} finally {
			context.close();
		}
	}

	public boolean supports(Class<?> clazz) {
		return String.class.equals(clazz);
	}
	
	public boolean isValidEmailAddress(String email) {
	       java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");
	       java.util.regex.Matcher m = p.matcher(email);
	       return m.matches();
	}
	
}
