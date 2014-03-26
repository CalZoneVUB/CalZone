package com.vub.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.dao.UserDao;
import com.vub.model.Globals;
import com.vub.model.User;

@Controller
public class ProfilePageControler {

	@RequestMapping(value = {"/*","/home"}, method = RequestMethod.GET)
	public String viewProfil(ModelMap model, Principal principal) {

		return "home";
	}
}
