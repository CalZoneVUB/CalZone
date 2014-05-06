package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PublisherController {

	@RequestMapping(value = "/publisher", method = RequestMethod.GET)
	public String publishSystemMessage(Model model) {
		return "Publisher";
	}
}