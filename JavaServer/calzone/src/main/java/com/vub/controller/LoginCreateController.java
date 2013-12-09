package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//import com.vub.model.Credentials;
import com.vub.model.User;
import com.vub.model.UserDao;

 
//@RequestMapping("/login")
@Controller
public class LoginCreateController {
	
	@RequestMapping(value = "/login/create", method = RequestMethod.GET)
	public String showLoginCreate(@ModelAttribute("user") User user) {
		System.out.println("loginCreateAccount GET");
		return "loginCreateAccount";
	}

	@RequestMapping(value = "/login/create", method = RequestMethod.POST)
	public String processLoginCreate(@ModelAttribute("user") User user) {
		System.out.println("LoginCreateController: processLoginCreate();");
		System.out.println("Asking to create new user with credentials: "+ user);
		
		//TODO check for same password in .jsp file
		UserDao userDao= new UserDao();
		System.out.println("Saving user to database: " + user);
		userDao.insertNotRegisteredUser(user);
		
		return "redirect:/login/create/successful/" + user.getUserName();
	}

	@RequestMapping(value="/login/successful")
	public String succesfullLogin() {
		System.out.println("/login/successful");
		return "successfulLogin";
	}

	@RequestMapping(value="/login/create/successful/{name}")
	public String succesfullCreationName(@PathVariable String name) {
		System.out.println("/login/create/successful");
		System.out.println(name);
		return "succesfullCreationAccount";
	}
	
	@RequestMapping(value="/login/create/successful")
	public String succesfullCreation() {
		System.out.println("/login/create/successful");
		return "succesfullCreationAccount";
	}
}
