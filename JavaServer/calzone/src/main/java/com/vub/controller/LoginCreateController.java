package com.vub.controller;

//import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;


import com.vub.dao.ActivationKeyDao;
import com.vub.dao.UserDao;
import com.vub.model.ActivationKey;
import com.vub.model.Globals;
import com.vub.model.MailMail;
//import com.vub.model.Credentials;
import com.vub.model.User;

@Controller
public class LoginCreateController {
	
	@RequestMapping(value = "/login/create", method = RequestMethod.GET)
	public String showLoginCreate(Model model) {
		if (Globals.DEBUG == 1) 
			System.out.println("loginCreateAccount GET");
		model.addAttribute("user", new User());
		return "loginCreateAccount";
	}

	@RequestMapping(value = "/login/create", method = RequestMethod.POST)
	public String processLoginCreate(Model model, @Valid User user,
			BindingResult result) {
		UserDao userDao = new UserDao();
		
		if (result.hasErrors()) { // Errors in one of the required fields
			if (Globals.DEBUG == 1) 
				System.out.println("Form does not validate");
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				if (Globals.DEBUG == 1) 
					System.out.println(error);
			}
			return "loginCreateAccount";
		} else {
			if (Globals.DEBUG == 1) 
				System.out.println("Creating Unregistered User");
			ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
			user.setPassword(encoder.encodePassword(user.getPassword(), null));
			if (Globals.DEBUG == 1) {
				System.out.println("New Password is: " + user.getPassword());
			}
			userDao.insertNotEnabledUser(user); // Adding user to DB as
													// unactivated user
			ActivationKeyDao activationKeyDao = new ActivationKeyDao();
			ActivationKey activationKey = new ActivationKey(user.getUserName());
			activationKeyDao.insertActivationKey(activationKey); // Adding
																	// activation
																	// key to DB

			if (Globals.DEBUG == 1) 
				System.out.println("Sending message to activate with key: " + activationKey + "to " + user.getEmail());

			ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
			
			
	    	MailMail mm = (MailMail) context.getBean("mailMailActivation");
	    	String siteRoot = mm.getSiteRoot() + "activate/";
	        mm.sendMail(user.getEmail(), "CalZone Activation", user.getFirstName() + 
	        		" " + user.getLastName(), siteRoot + activationKey.getKeyString());
	        
	        ((ClassPathXmlApplicationContext) context).close(); 
			return "ActivateYourAccount";
		}
	}

	@RequestMapping(value = "/login/successful")
	public String succesfullLogin() {
		if (Globals.DEBUG == 1) 
			System.out.println("/login/successful");
		return "successfulLogin";
	}

	@RequestMapping(value = "/login/create/successful/{name}")
	public String succesfullCreationName(@PathVariable String name) {
		if (Globals.DEBUG == 1) {
			System.out.println("/login/create/successful");
			System.out.println(name);
		}
		return "successfulCreationAccount";
	}

	@RequestMapping(value = "/login/create/successful")
	public String succesfullCreation() {
		if (Globals.DEBUG == 1) 
			System.out.println("/login/create/successful");
		return "succesfullCreationAccount";
	}
}
