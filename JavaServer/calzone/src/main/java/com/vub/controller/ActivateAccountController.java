package com.vub.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vub.model.ActivationKey;
import com.vub.model.ActivationKeyDao;
import com.vub.model.SessionDao;
//import com.vub.model.Credentials;
import com.vub.model.User;
import com.vub.model.UserDao;

@Controller
public class ActivateAccountController {

	@RequestMapping(value = "/activate/{keyString}", method = RequestMethod.GET)
	public String activateUser(Model model, @PathVariable String keyString,
			HttpServletRequest request) {
		System.out.println("Activation user contoller");

		ActivationKeyDao activationKeyDao = new ActivationKeyDao();
		ActivationKey activationKey = activationKeyDao.findByKeyString(keyString);

		System.out.println("Found by keyString: " + activationKey);
		
		if (activationKey == null) {
			System.out.println("keyString not found in DB. No account activated");
			return "ActivatedNotAccount";
		} else {
			UserDao userDao = new UserDao();
			User user = new User();
			user = userDao.findByNotRegisteredUserName(activationKey.getUserName());
			System.out.println("Found user by actiavtion key: " + user);
			if (user == null) {
				System.out.println("no user found with username in system for activation key");
				return "ActivatedNotAccount";
			} else {
				userDao.upgradeNotRegisteredUser(user);
				activationKeyDao.deleteActivationKey(activationKey);
				return "ActivatedAccount";
			}
		}
	}
}