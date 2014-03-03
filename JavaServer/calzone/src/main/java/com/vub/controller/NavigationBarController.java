package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping("/NavigationBar")
@Controller
public class NavigationBarController {

	// Serving temp Navigation Bar Page
	@RequestMapping(value = "/NavigationBar", method = RequestMethod.GET)
	public String navigationBarPage(ModelMap model) {
		return "NavigationBar";
	}

}
