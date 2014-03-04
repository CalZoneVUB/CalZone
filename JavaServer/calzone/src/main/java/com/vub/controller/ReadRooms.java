package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.datadump.ReadCSV;

@Controller 
public class ReadRooms {
	@RequestMapping(value = "/readrooms")
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello World");
		
		ReadCSV csv = new ReadCSV();
		//csv.readRoom();
		
		return "hello";
	}
}
