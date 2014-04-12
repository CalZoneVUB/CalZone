package com.vub.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vub.datadump.LoadDump;
import com.vub.datadump.ReadCSV;
import com.vub.model.Course;

@Controller 
public class ReadCSVController {
	@RequestMapping(value = "/readCSV")
	public String readCSV(Model model) {
		model.addAttribute("greeting", "Hello World");
		
		//ReadCSV csv = new ReadCSV();
		//csv.readRoom("Leslokalen.csv",";");
		
		//LoadDump loadDump = new LoadDump();
		//ArrayList<Course> listCourses = loadDump.loadCourses();
				
		return "hello";
	}
}



