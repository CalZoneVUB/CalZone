
package com.vub.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.exception.KeyNotFoundException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Email;
import com.vub.model.Globals;
import com.vub.model.Key;
import com.vub.model.Key.KeyPermissionEnum;
import com.vub.model.MailMail;
import com.vub.model.Password;
import com.vub.model.PasswordKey;
import com.vub.model.User;
import com.vub.service.KeyService;
import com.vub.service.MailService;
import com.vub.service.UserService;
import com.vub.validators.EmailBelongsToUserValidator;



@Controller
public class PasswordForgetController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/passwordforgot", method = RequestMethod.GET)
	public String initForm(Model model) {
		model.addAttribute("email", new Email());
		return "passwordforgot"; 
	}

	@RequestMapping(value = "/passwordforgot" , method = RequestMethod.POST)
	public String processSumit(Model model, @ModelAttribute("email") String email, BindingResult result) {	
		// Validate if the e-mail address belongs to a certain user
		EmailBelongsToUserValidator validator = new EmailBelongsToUserValidator();
		validator.validate(email, result);

		if (result.hasErrors()) { // Errors in one of the required fields
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				logger.error("Error in e-mail address: " + error.toString());
			}
			return "passwordforgot";
		}
		else {
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			MailService mailService = (MailService) context.getBean("mailService");
			KeyService keyService = (KeyService) context.getBean("keyService");
			UserService userService = (UserService) context.getBean("userService");

			try {
				// Get the user attached to the provided e-mail address
				User user = userService.findUserByEmail(email);
				// Find all keys currently assigned to the user (he may already have a passwordforgot key)
				List<Key> assignedKeys = keyService.findKeysAssignedToUser(user);

				// Try to find a passwordforgot key
				Key validKey = null;
				for(Key k : assignedKeys)
					if(k.getKeyPermission() == KeyPermissionEnum.PasswordReset)
						validKey = k;

				// If no password key could be found, generate a new one (it's immediately saved to the db)
				if (validKey == null)
					validKey = keyService.generatePasswordForgotKey(user);

				// Send a mail to the user
				mailService.sendPasswordForgotMail(user, validKey);
			} catch (UserNotFoundException e) {
				// Catch the user not found exception (i.e. no user could be found with the provided email)
				return "passwordforgot";
			} finally {
				context.close();
			}
			return "passwordSuccess";  
		}
	}

	@RequestMapping(value = "/passwordforgot/{key}" , method = RequestMethod.GET)
	public String intiForm(Model model, @PathVariable String key) {
		Password password = new Password();
		model.addAttribute("password", password);

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		KeyService keyService = (KeyService) context.getBean("keyService");

		try {
			// Suppress the warning, because the usage of pwkey doesn't matter - it's the exception (which may not happen) that matters
			@SuppressWarnings("unused")
			Key pwKey = keyService.findKey(key);
			// Make sure the key is actually valid for resetting the password
			if(pwKey.getKeyPermission() != Key.KeyPermissionEnum.PasswordReset)
				return "passwordForgotFail";
		} catch (KeyNotFoundException ex) {
			// When the key cannot be found in the DB, the password cannot be changed
			return "passwordForgotFail";
		} finally {
			// Don't forget to close the application context!
			context.close();
		}
		return "passwordForgotApply";
	}

	@RequestMapping(value = "/passwordforgot/{key}" , method = RequestMethod.POST)
	public String submitForm(@Valid Password password, BindingResult result, Model model, @PathVariable String key) {
		if (result.hasErrors()) { // Errors in one of the required fields
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				logger.error("Error in password binding: " + error.toString());
			}
			return "passwordForgotApply";
		}
		else {
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			KeyService keyService = (KeyService) context.getBean("keyService");
			UserService userService = (UserService) context.getBean("userService");
			
			ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
			String shaPassword = encoder.encodePassword(password.getPassword(), null);
			
			try {
				// Find the password forgot key.
				Key pwKey = keyService.findKey(key);
				// Find the user attached to the key
				User user = keyService.findUserByKey(pwKey.getKeyString());
				// Set the new password on the user
				user.setPassword(shaPassword);
				// Save the user to the database
				userService.updateUser(user);
				// Delete the key from the database
				keyService.deleteKey(pwKey);
				
			} catch (KeyNotFoundException ex) {
				// Ehh.. This shouldn't really happen
			} catch (UserNotFoundException e) {
				// This shouldn't really happen either, but oh well.
			} finally {
				context.close();
			}

			return "redirect:/";
		}
	}

}