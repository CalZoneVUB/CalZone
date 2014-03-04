package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class Test3 {
	@RequestMapping(value = "/test3")
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello World");
		return "hello";
	}
}
