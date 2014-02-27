package com.vub.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.vub.model.User;
import com.vub.model.UserDao;

//@RequestMapping("/AddCourse")
@Controller
public class AddCourseController {

	// Serving Login Page
	@RequestMapping(value = "/AddCourse", method = RequestMethod.GET)
	public String mainPage(ModelMap model) {
		return "AddCourse";
	}
	
	@RequestMapping(value = "/AddCourse", method = RequestMethod.POST)
	public String SubmitCourse(ModelMap model) {
		return "ConfirmCourse";
	}

}
