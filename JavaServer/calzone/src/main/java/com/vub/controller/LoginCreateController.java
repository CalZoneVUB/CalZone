package com.vub.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vub.model.ActivationKey;
import com.vub.model.ActivationKeyDao;
import com.vub.model.MailMail;
//import com.vub.model.Credentials;
import com.vub.model.User;
import com.vub.model.UserDao;

@Controller
public class LoginCreateController {
	
	@RequestMapping(value = "/login/create", method = RequestMethod.GET)
	public String showLoginCreate(Model model) {
		System.out.println("loginCreateAccount GET");
		model.addAttribute("user", new User());
		return "loginCreateAccount";
	}

	@RequestMapping(value = "/login/create", method = RequestMethod.POST)
	public String processLoginCreate(Model model, @Valid User user,
			BindingResult result) {
		UserDao userDao = new UserDao();
		
		String siteRoot = "localhost:8080/calzone/activate/";
		
		if (result.hasErrors()) { // Errors in one of the required fields
			System.out.println("Form does not validate");
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				System.out.println(error);
			}
			return "loginCreateAccount";
		} else {
			System.out.println("Creating Unregistered User");
			userDao.insertNotRegisteredUser(user); // Adding user to DB as
													// unactivated user
			ActivationKeyDao activationKeyDao = new ActivationKeyDao();
			ActivationKey activationKey = new ActivationKey(user.getUserName());
			activationKeyDao.insertActivationKey(activationKey); // Adding
																	// activation
																	// key to DB

			System.out.println("TODO: Sending message to activate with key: " + activationKey + "to " + user.getEmail());
			System.out.println("Sending Email to test account timbowitters@gmail.com");

			ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
			
	    	MailMail mm = (MailMail) context.getBean("mailMail");
	        mm.sendMail(user.getEmail(), "CalZone Activation", user.getFirstName() + 
	        		" " + user.getLastName(), siteRoot + activationKey.getKeyString());
	        
	        ((ClassPathXmlApplicationContext) context).close(); 
			return "ActivateYourAccount";
		}
	}

	@RequestMapping(value = "/login/successful")
	public String succesfullLogin() {
		System.out.println("/login/successful");
		return "successfulLogin";
	}

	@RequestMapping(value = "/login/create/successful/{name}")
	public String succesfullCreationName(@PathVariable String name) {
		System.out.println("/login/create/successful");
		System.out.println(name);
		return "successfulCreationAccount";
	}

	@RequestMapping(value = "/login/create/successful")
	public String succesfullCreation() {
		System.out.println("/login/create/successful");
		return "succesfullCreationAccount";
	}
}
