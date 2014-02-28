package com.vub.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.vub.model.Credentials;
//import com.vub.model.User;

@Controller
public class ProfilePageControler {

	@RequestMapping(value= "/profile", method = RequestMethod.GET)
	public String viewProfil(ModelMap model, Principal principal) {

		String name = principal.getName();
		model.addAttribute("username", name);
		return "profile";
	}
}
