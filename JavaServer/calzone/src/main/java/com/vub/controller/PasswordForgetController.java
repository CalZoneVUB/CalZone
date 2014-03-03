
package com.vub.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.dao.PasswordKeyDao;
import com.vub.dao.UserDao;
import com.vub.model.Email;
import com.vub.model.Globals;
import com.vub.model.MailMail;
import com.vub.model.Password;
import com.vub.model.PasswordKey;
import com.vub.model.User;
import com.vub.validators.EmailBelongsToUserValidator;



@Controller
public class PasswordForgetController {

	@RequestMapping(value = "/passwordforgot", method = RequestMethod.GET)
	public String initForm(Model model) {
		
		model.addAttribute("email", new Email());
		return "passwordforgot"; 
	}

	@RequestMapping(value = "/passwordforgot" , method = RequestMethod.POST)
	public String processSumit(Model model, @ModelAttribute("email") Email email, BindingResult result) {
		EmailBelongsToUserValidator validator = new EmailBelongsToUserValidator();
		validator.validate(email, result);
		
		if (result.hasErrors()) { // Errors in one of the required fields
			List<ObjectError> errors = result.getAllErrors();
			if(Globals.DEBUG == 1)
				System.out.println("-- Errors exist in one or more required fields --");
			for (ObjectError error : errors)
				if (Globals.DEBUG == 1)
					System.out.println(error);
			return "passwordforgot";
		}
		else {
			String emailString = email.getEmail(); // Get the provided e-mail address
			try {
				// Get the BeanFactory, and retrieve the mailMail bean, which provides configuration options, 
				// and also access to the class which can be used to send the actual e-mail
				ApplicationContext contextMail = new ClassPathXmlApplicationContext("Spring-Mail.xml");
				MailMail mailSender = (MailMail) contextMail.getBean("mailMailPassword");
				
				PasswordKey passwordKey = new PasswordKey(emailString); //Create a new key based on the e-mail address
				PasswordKeyDao passwordKeyDao = new PasswordKeyDao(); // Get the capabilities to store the key in the database (because it needs activation by the user)
				passwordKeyDao.insert(passwordKey); // Store key in database

				UserDao userDao = new UserDao(); // Get the capabilities to retrieve users from the database
				User user = userDao.findByEmail(emailString); // Get the user attached to the provided e-mail address

				
				String URL = mailSender.getSiteRoot() + "passwordforgot/" + passwordKey.getKeyString();
				mailSender.sendMail(user.getEmail(), "CalZone Password Password Recovery",
							user.getFirstName() + " " + user.getLastName(), URL);
				
				((ConfigurableApplicationContext) contextMail).close();

			} catch (NullPointerException e) {
				System.out.println("NullPointerExeption in PasswordForget Controller");
				return "passwordforgot";
			}
			return "passwordSuccess";  
		}
	}

	@RequestMapping(value = "/passwordforgot/{key}" , method = RequestMethod.GET)
	public String intiForm(Model model ,@PathVariable String key) {
		if (Globals.DEBUG == 1) {System.out.println("/passwordforgot/key");}
		Password password = new Password();
		model.addAttribute("password", password);

		PasswordKeyDao passwordKeyDao = new PasswordKeyDao();
		PasswordKey passwordKey = passwordKeyDao.findByKeyString(key);

		if (passwordKey == null) // return null when key doens't exists
			return "passwordForgotFail";
		else
			return "passwordForgotApply";
	}

	@RequestMapping(value = "/passwordforgot/{key}" , method = RequestMethod.POST)
	public String submitForm(@Valid Password password, BindingResult result, Model model, @PathVariable String key) {
		if (result.hasErrors()) { // Errors in one of the required fields
			if (Globals.DEBUG == 1) System.out.println("Form does not validate");
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				if (Globals.DEBUG == 1) System.out.println(error);}
			return "passwordForgotApply";
		}
		else {
			ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
			String shaPassword = encoder.encodePassword(password.getPassword(), null);

			PasswordKeyDao passwordKeyDao = new PasswordKeyDao();
			PasswordKey passwordKey = passwordKeyDao.findByKeyString(key);
			String email = passwordKey.getIdentifier();

			UserDao userDao = new UserDao();
			User user = userDao.findByEmail(email);

			user.setPassword(shaPassword);
			userDao.updateUser(user);

			passwordKeyDao.delete(passwordKey);


			return "";
		}
	}

}