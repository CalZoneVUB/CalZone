package com.vub.controller;

//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;


import com.vub.dao.ActivationKeyDao;
import com.vub.dao.UserDao;
import com.vub.model.ActivationKey;
import com.vub.model.Globals;
//import com.vub.model.Credentials;
import com.vub.model.User;
import com.vub.model.Globals;

@Controller
public class ActivateAccountController {

	@RequestMapping(value = "/activate/{keyString}", method = RequestMethod.GET)
	public String activateUser(Model model, @PathVariable String keyString,
			HttpServletRequest request) {
		if (Globals.DEBUG == 1) 
			System.out.println("Activation user contoller");

		ActivationKeyDao activationKeyDao = new ActivationKeyDao();
		ActivationKey activationKey = activationKeyDao.findByKeyString(keyString);

		if (Globals.DEBUG == 1) 
			System.out.println("Found by keyString: " + activationKey);
		
		if (activationKey == null) {
			if (Globals.DEBUG == 1) System.out.println("keyString not found in DB. No account activated");
			
			return "ActivatedNotAccount";
		} else {
			UserDao userDao = new UserDao();
			User user = new User();
			user = userDao.findByUserName(activationKey.getUserName());
			if (Globals.DEBUG == 1) System.out.println("Found user by actiavtion key: " + user);
			
			if (user == null) {
				if (Globals.DEBUG == 1) System.out.println("no user found with username in system for activation key");
				
				return "ActivatedNotAccount";
			} else {
				userDao.upgradeNotEnabledUser(user);
				activationKeyDao.deleteActivationKey(activationKey);
				return "ActivatedAccount";
			}
		}
	}
}