package com.vub.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.vub.model.ActivationKey;
import com.vub.model.EmailMe;
import com.vub.model.Globals;
import com.vub.model.PasswordKey;
import com.vub.model.PasswordKeyDao;
//import com.vub.model.Credentials;
import com.vub.model.User;
 
@Controller
public class PasswordForgetController {
	
	
	@RequestMapping(value = "/passwordforgot", method = RequestMethod.GET)
	public String initForm(Model model) {
		if (Globals.DEBUG == 1) {System.out.println("Serving /pasword");
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		ActivationKey key = (ActivationKey) context.getBean("passwordKey");
		System.out.println(key);
		context.close();}
		
		model.addAttribute("emailMe", new EmailMe());
		return "passwordforgot"; 
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSumit(Model model, @Valid EmailMe emailMe,
			BindingResult result) {
		System.out.println("Email found by form: " + emailMe);
		
		if (result.hasErrors()) { // Errors in one of the required fields
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				if (Globals.DEBUG == 1) System.out.println(error);}
			if (Globals.DEBUG == 1) {
				System.out.println("Email not real or blank.");
			}
			return "passwordforgot";
		}
		else {
			
			ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
			PasswordKeyDao passwordKeyDao = (PasswordKeyDao) context.getBean("passwordKeyDao");
			PasswordKey passwordKey = new PasswordKey(emailMe.getEmail());
			passwordKeyDao.insert(passwordKey);
			return "passwordSuccess";  
		}
	}
	
	@RequestMapping(value = "/passwordforgot/{key}" , method = RequestMethod.GET)
	public String processNewPassword() {
		
		if (Globals.DEBUG == 1) {System.out.println("/passwordforgot/key");}
		return "passwordforgotSuccess";
	}
}