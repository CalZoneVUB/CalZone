package com.vub.validators;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import com.vub.dao.UserDao;
import com.vub.model.Email;
import com.vub.model.User;

/**
 * Implements the Validator interface and checks whether a given Email class is a valid e-mail address, which belongs to a registered user.
 * 
 * @author Sam Van den Vonder
 *
 */

public class EmailBelongsToUserValidator implements Validator {  
	public void validate(Object obj, Errors e) {
		Email email = (Email) obj;
		String emailString = email.getEmail();
		
		UserDao userDao = new UserDao();
		User user = userDao.findByEmail(emailString);
		System.out.println("checking email " + emailString);
		
		//ValidationUtils.rejectIfEmpty(e, "email", "email.blankorinvalid.text");
		if(!isValidEmailAddress(emailString))
			e.rejectValue("email", "email.blankorinvalid.text");
		else if(user == null)
			e.rejectValue("email", "passwordforgot.emailunavailable.text");			
	}

	public boolean supports(Class<?> clazz) {
		return Email.class.equals(clazz);
	}
	
	public boolean isValidEmailAddress(String email) {
	       java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");
	       java.util.regex.Matcher m = p.matcher(email);
	       return m.matches();
	}
	
}
