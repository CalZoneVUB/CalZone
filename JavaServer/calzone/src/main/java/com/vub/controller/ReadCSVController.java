package com.vub.controller;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.datadump.LoadDump;
import com.vub.datadump.ReadCSV;
import com.vub.exception.CourseComponentNotFoundException;
import com.vub.exception.CourseNotFoundException;
import com.vub.exception.FloorNotFoundException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Floor;
import com.vub.model.Room;
import com.vub.model.User;
import com.vub.service.CourseComponentService;
import com.vub.service.CourseService;
import com.vub.service.FloorService;
import com.vub.service.UserService;

@Controller 
public class ReadCSVController {
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value = "/readCSV")
	public String readCSV(Model model) {
		model.addAttribute("greeting", "Hello World");
		
		try {
			Course c = courseService.findCourseById(31);
			for(CourseComponent cc :c.getCourseComponents()){
				System.out.println(cc.getTeachers());
			}
			System.out.println(courseService.getAllEntries(c));
		} catch (CourseNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "hello";
	}
}



