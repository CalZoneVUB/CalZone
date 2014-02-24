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

import com.vub.model.CookieSession;
import com.vub.model.SessionDao;
//import com.vub.model.Credentials;
import com.vub.model.User;
import com.vub.model.UserDao;

@Controller
public class ProfilePageControler {

	@RequestMapping(value= "/profile", method = RequestMethod.GET)
	public String viewProfileByCookie(Model model,HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();  //Ophalen cookies van browser
		CookieSession cookieSession = new CookieSession(cookies); //eventueel inlezen van juiste sessie

		if (cookieSession.getSession() == null) {
			System.out.println("No cookies found in browser");
			return "redirect:/login";
		} else {
			return "redirect:/profile/" + cookieSession.getSession().getUserName();
		}
	}
	
	@RequestMapping(value = "/profile/{userName}", method = RequestMethod.GET)
	public ModelAndView viewProfile(@ModelAttribute("user") User user,
			@PathVariable String userName, HttpServletRequest request) {
		System.out.println("/profile POST");
		// TODO - Fill in the data of the user object, use try/catch -
		// the fill-in method needs to throw an exception for unknown user

		ModelAndView view = new ModelAndView("profile", "user", user);
	
		
		Cookie[] cookies = request.getCookies();
		CookieSession cookieSession = new CookieSession(cookies);
		if (userName == null) {
			return view;
		}
		else if (cookieSession.getSession() == null) {
			view = new ModelAndView("AccessDenied");
			return view;
		} 
		else if (cookieSession.hasAccess(userName)) {
			System.out.println("Loading Profile");
			if (cookieSession.getUser() != null) {
				view.addObject("username", cookieSession.getUser().getUserName());
				view.addObject("firstname", cookieSession.getUser().getFirstName());
				view.addObject("lastname", cookieSession.getUser().getLastName());
				view.addObject("email", cookieSession.getUser().getEmail());
				System.out.println("Showing profile of" + cookieSession.getUser());
				System.out.println(view);
				return view;
			}
		}
		else {
			view = new ModelAndView("AccessDenied");
			return view;
		}
		return view;
		
		// TODO - Check if the guy has the rights to view the profile
		// http://khangaonkar.blogspot.in/2011/04/spring-security-tutorial-1.html
		// - using the spring security framework?
		// Demonstrates two methods to get values to the JSP.
		// First, we can pass the user object (accessed via the "user" string in
		// the jsp)
		// Second, we can create key/value bindings using addObject

	}
}
