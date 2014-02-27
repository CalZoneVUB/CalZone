package com.vub.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.vub.model.ActivationKey;
import com.vub.model.EmailMe;
import com.vub.model.Globals;
import com.vub.model.MailMail;
import com.vub.model.Password;
import com.vub.model.PasswordKey;
import com.vub.model.PasswordKeyDao;
import com.vub.model.User;
import com.vub.model.UserDao;
 
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
	
	@RequestMapping(value = "/passwordforgot" , method = RequestMethod.POST)
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
			try {
				ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
				ApplicationContext contextMail = new ClassPathXmlApplicationContext("Spring-Mail.xml");
				PasswordKeyDao passwordKeyDao = (PasswordKeyDao) context.getBean("passwordKeyDao");
				PasswordKey passwordKey = new PasswordKey(emailMe.getEmail());
				passwordKeyDao.insert(passwordKey);
				
				UserDao userDao = new UserDao();
				User user = userDao.findByEmail(passwordKey.getIdentifier());
				
				MailMail mm = (MailMail) contextMail.getBean("mailMail");
				
				String siteRoot = mm.getSiteRoot() + "passwordforgot/";
				mm.sendMail(user.getEmail(), "CalZone Password Password Recovery", user.getFirstName() + 
		        		" " + user.getLastName(), siteRoot + passwordKey.getKeyString());
				
				((ConfigurableApplicationContext) context).close();
				((ConfigurableApplicationContext) contextMail).close();
				
			} catch (NullPointerException e) {
				System.out.println("NullPointerExeption in PasswordForget Controller");
				return "passwordforgot";
				}
			return "passwordSuccess";  
		}
	}
	
	@RequestMapping(value = "/passwordforgot/{key}" , method = RequestMethod.GET)
	public String intiForm(Model model) {
		if (Globals.DEBUG == 1) {System.out.println("/passwordforgot/key");}
		Password password = new Password();
		model.addAttribute("password", password);
		//TODO Check if key exists
		
		return "passwordForgotApply";
	}
	
	@RequestMapping(value = "/passwordforgot/{key}" , method = RequestMethod.POST)
	public String submitForm(@Valid Password password, BindingResult result, Model model, @PathVariable String key) {
		if (Globals.DEBUG == 1) {System.out.println("Pass1: " + password.getPassword() + " Pass2: " + password.getConfirmPassword() );}
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		String shaPassword = encoder.encodePassword(password.getPassword(), null);
		
		//TODO Check dubble password same
		//TODO Serve to login when success fail to try again.
		//return "paswordNewSuccess";
		return "passwordForgotApply";
	}
	
}