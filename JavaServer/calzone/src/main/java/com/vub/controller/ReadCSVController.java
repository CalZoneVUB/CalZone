package com.vub.controller;

import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.exception.CourseComponentNotFoundException;
import com.vub.exception.CourseNotFoundException;
import com.vub.exception.FloorNotFoundException;
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
		
		
		
		
		// TEST CODE
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseComponentService courseComponentService = (CourseComponentService) context.getBean("courseComponentService");
		UserService userService = (UserService) context.getBean("userService");
		CourseService courseService = (CourseService) context.getBean("courseService");
		FloorService floorService = (FloorService) context.getBean("floorService");
		
		try {
			Course c = courseService.findCourseByIdInitialized(3);
			System.out.println("Course with id=3 :" + c);
			System.out.println("-> # courseComponents = " + c.getCourseComponents().size());
			for(CourseComponent cp: c.getCourseComponents()){
				System.out.println("--> courseComponent : " + cp);
				Set<User> teachers = cp.getTeachers();
				for(User teacher: teachers){
					System.out.println("---> teacher : " + teacher);
				}
			}
			Floor f = floorService.getFloorInitialized(0, "D");
			System.out.println("\nFloor 0 from Building D:");
			for(Room r:f.getRooms()){
				System.out.println("-> Room: " + r.getName());
			}
			//remove 1
			//teachers.clear();
			//teachers.remove(userService.findUserByID(3)); // DIT HEEFT GEEN EFFECT OP DATABASE MOMENTEEL !
			//cp.setTeachers(teachers);
			//courseComponentService.updateCourseComponent(cp);
			//CourseComponent cp2 = courseComponentService.findCourseComponentById(2); 
			//System.out.println(cp2.getTeachers().toString());
			//System.out.println(cp2.getTeachers().size());
		//} catch (CourseComponentNotFoundException ex) {
		//	System.out.println(ex.toString());
		//} catch (UserNotFoundException e) {
		//	System.out.println(e.toString());
		} catch (CourseNotFoundException e2) {
			System.out.println(e2.toString());
		} catch (FloorNotFoundException ef) {
			System.out.println(ef.toString());
		} finally {
			context.close();
		}
		
		
		return "hello";
	}
}



