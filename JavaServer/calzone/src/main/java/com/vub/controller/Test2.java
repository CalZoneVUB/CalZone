package com.vub.controller;

import java.util.ArrayList;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vub.datadump.LoadDump;
import com.vub.datadump.ReadCSV;
import com.vub.model.ActivationKey;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Professor;
import com.vub.model.Room;

@Controller 
public class Test2 {
	@RequestMapping(value = "/test2")
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello World");
		
		//ReadCSV csv = new ReadCSV();
		//ArrayList<Professor> professorList = csv.readProfessor("INSTR_NAME.csv",";");
		//ArrayList<Room> roomList = csv.readRoom("Leslokalen.csv",";");
		//ArrayList<Course> courseListId = csv.readCourceId("CRSE_ID.csv",";");
		
		//Gson gson = new Gson();
		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//System.out.println(gson.toJson(courseListId));
		
		LoadDump loadDump = new LoadDump();
		ArrayList<Course> listCourses = loadDump.loadCourses();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//System.out.println(gson.toJson(listCourses));
		return "hello";
	}
}
