package com.vub.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.datadump.LoadDump;
import com.vub.datadump.ReadCSV;
import com.vub.model.Course;

@Controller 
public class ReadCSVController {
	@RequestMapping(value = "/readCSV")
	public String readCSV(Model model) {
		model.addAttribute("greeting", "Hello World");
		
		
		ReadCSV csv = new ReadCSV();
		csv.readRoom("Leslokalen.csv",";");
		
		System.out.println("$$$$$$$$$$$$$$$$$ ROOMS LOADED $$$$$$$$$$$$$$$$$");
		
		LoadDump loadDump = new LoadDump();
		ArrayList<Course> listCourses = loadDump.loadCourses();

		System.out.println("$$$$$$$$$$$$$$$$ COURSES LOADED $$$$$$$$$$$$$$$$");
		
		// TEST CODE
		/*ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		UserService userService = (UserService) context.getBean("userService");
		
		try {
			CourseComponent cp = courseService.findCourseComponentById(2);
			cp.setTeachers(userService.getAllUsers());
			courseService.updateCourseComponent(cp);
			
			CourseComponent cp2 = courseService.findCourseComponentById(2); 
			System.out.println(cp2.getTeachers().toString());
		} catch (CourseComponentNotFoundException ex) {
			System.out.println(ex.toString());
		} finally {
			context.close();
		}*/
		
		
		return "hello";
	}
}



