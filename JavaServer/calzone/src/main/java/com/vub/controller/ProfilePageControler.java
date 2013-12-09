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

//import com.vub.model.Credentials;
import com.vub.model.User;
import com.vub.model.UserDao;

@Controller
public class ProfilePageControler {

	@RequestMapping(value = "/profile/{userName}", method = RequestMethod.GET)
	public ModelAndView viewProfile(@ModelAttribute("user") User user,
			@PathVariable String userName, HttpServletRequest request) {
		System.out.println("/profile POST");
		// TODO - Fill in the data of the user object, use try/catch -
		// the fill-in method needs to throw an exception for unknown user

		ModelAndView view = new ModelAndView("profile", "user", user);

		UserDao userDao = new UserDao();
		User user2 = userDao.findByUserName(userName);
		if (user2 != null) {
			user = user2;
			view.addObject("username", user.getUserName());
			view.addObject("firstname", user.getFirstName());
			view.addObject("lastname", user.getLastName());
			view.addObject("email", user.getEmail());
			System.out.println("Showing profile of" + user);
		}
		
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			System.out.println("No cookies found in browser");
		} else {
			for (Cookie retrieveCookie : cookies) {
				String cookieName = retrieveCookie.getName();
				String cookieValue = retrieveCookie.getValue();
				if (cookieName.equals("CalzoneSessionKey")) {
					System.out.println("Calzone Cookie with name: " + cookieName + " and value: " + cookieValue);
				}
				else {
					System.out.println("Other Cookie with name: " + cookieName + " and value: " + cookieValue);
				}
			}
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
