package com.vub.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.vub.model.ActivationKey;
import com.vub.model.Email;
import com.vub.model.Globals;
//import com.vub.model.Credentials;
import com.vub.model.User;
 
@Controller
public class PasswordForgetController {
	
	
	@RequestMapping(value = "/passwordforgot", method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		if (Globals.DEBUG == 1) {System.out.println("Serving /pasword");
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		ActivationKey key = (ActivationKey) context.getBean("passwordKey");
		System.out.println(key);
		context.close();}
		
		model.addAttribute("email", new Email());
		
		return "passwordforgot"; 
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSumit(Model model, Email email,
			BindingResult result) {
		System.out.println("Email found by form: " + email);
		return "passwordSuccess"; 
	}
	
	@RequestMapping(value = "/passwordforgot/{key}" , method = RequestMethod.GET)
	public String processNewPassword() {
		
		if (Globals.DEBUG == 1) {System.out.println("/passwordforgot/key");}
		return "passwordforgotSuccess";
	}
}