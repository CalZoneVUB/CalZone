package com.vub.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.datadump.LoadDump;
import com.vub.model.Course;

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
		
		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//System.out.println(gson.toJson(listCourses));
		
		
		/*
		Set<Professor> hash = new HashSet<Professor>();
		User user = new User();
		user.setUserName("userName1");
		user.setEmail("userName1@gmail.com");
		Professor prof = new Professor(user);
		prof.setiD(1);
		hash.add(prof);
		
		User user2= new User();
		user2.setUserName("userName");
		user2.setEmail("userName2@gmail.com");
		Professor prof2 = new Professor(user2);
		prof2.setiD(2);
		hash.add(prof2);
		
		User user3 = new User();
		user3.setUserName("userName1");
		user3.setEmail("userName1@gmail.com");
		Professor prof3 = new Professor(user3);
		prof3.setiD(1);
		hash.add(prof3);
		
		System.out.println(hash);*/
		return "hello";
	}
}



