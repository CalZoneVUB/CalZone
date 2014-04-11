package com.vub.controller;

import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.exception.CourseComponentNotFoundException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.CourseComponent;
import com.vub.model.User;
import com.vub.service.CourseComponentService;
import com.vub.service.UserService;

@Controller 
public class ReadCSVController {
	@RequestMapping(value = "/readCSV")
	public String readCSV(Model model) {
		model.addAttribute("greeting", "Hello World");
		
		/*
		ReadCSV csv = new ReadCSV();
		csv.readRoom("Leslokalen.csv",";");
		
		System.out.println("$$$$$$$$$$$$$$$$$ ROOMS LOADED $$$$$$$$$$$$$$$$$");
		
		LoadDump loadDump = new LoadDump();
		ArrayList<Course> listCourses = loadDump.loadCourses();

		System.out.println("$$$$$$$$$$$$$$$$ COURSES LOADED $$$$$$$$$$$$$$$$");
		*/
		
		/*
		
		
		// TEST CODE
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseComponentService courseComponentService = (CourseComponentService) context.getBean("courseComponentService");
		UserService userService = (UserService) context.getBean("userService");
		
		try {
			CourseComponent cp = courseComponentService.findCourseComponentById(2);
			Set<User> teachers = userService.getAllUsers();
			//remove 1
			//teachers.clear();
			//teachers.remove(userService.findUserByID(3)); // DIT HEEFT GEEN EFFECT OP DATABASE MOMENTEEL !
			//cp.setTeachers(teachers);
			courseComponentService.updateCourseComponent(cp);
			
			CourseComponent cp2 = courseComponentService.findCourseComponentById(2); 
			System.out.println(cp2.getTeachers().toString());
			System.out.println(cp2.getTeachers().size());
		} catch (CourseComponentNotFoundException ex) {
			System.out.println(ex.toString());
		} catch (UserNotFoundException e) {
			System.out.println(e.toString());
		} finally {
			context.close();
		}
		
		*/
		return "hello";
	}
}



