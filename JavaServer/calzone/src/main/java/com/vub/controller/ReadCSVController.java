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
		//CourseComponentService courseComponentService = (CourseComponentService) context.getBean("courseComponentService");
		//UserService userService = (UserService) context.getBean("userService");
		CourseService courseService = (CourseService) context.getBean("courseService");
		FloorService floorService = (FloorService) context.getBean("floorService");
		
		try {
			Course c = courseService.findCourseByIdInitialized(5);
			System.out.println("Course with id=5 :" + c);
			System.out.println("-> # courseComponents = " + c.getCourseComponents().size());
			for(CourseComponent cp: c.getCourseComponents()){
				System.out.println("--> courseComponent : " + cp);
				Set<User> teachers = cp.getTeachers();
				for(User teacher: teachers){
					System.out.println("---> teacher : " + teacher);
				}
			}
			
//			// TEST: REMOVE COURSECOMPONENT ID 5 ( EXM ) FROM COURSE 5.
//			System.out.println("===== REMOVE WORKING?");
//			c.getCourseComponents().remove(courseComponentService.findCourseComponentByIdInitialized(5));
//			c = courseService.updateCourse(c);
//			
//			for(CourseComponent cp: c.getCourseComponents()){
//				System.out.println("--> courseComponent : " + cp);
//				Set<User> teachers = cp.getTeachers();
//				for(User teacher: teachers){
//					System.out.println("---> teacher : " + teacher);
//				}
//			}
			
			// TEST TO SEE IF WE CAN REMOVE VIA UPDATE.
			
//			CourseComponent cp = courseComponentService.findCourseComponentByIdInitialized(4);
//			User user1 = userService.findUserByIdInitialized(1);
//			cp.getTeachers().remove(user1);
//			courseComponentService.updateCourseComponent(cp);
//			
//			cp = courseComponentService.findCourseComponentByIdInitialized(4);
//			Set<User> teachers = cp.getTeachers();
//			for(User teacher: teachers){
//			}
			
			// ALL ROOMS OF 1 FLOOR.
			
			Floor f = floorService.getFloorInitialized(0, "D", "VUB");
			System.out.println("\nFloor 0 from Building D:");
			for(Room r:f.getRooms()){
				System.out.println("-> Room: " + r.getName());
			}

		//} catch (UserNotFoundException e) {
		//	System.out.println(e.toString());
		} catch (CourseNotFoundException e2) {
			System.out.println(e2.toString());
		} catch (FloorNotFoundException ef) {
			System.out.println(ef.toString());
		//} catch (CourseComponentNotFoundException ecc) {
		//	System.out.println(ecc.toString());
		} finally {
			context.close();
		}
		
		*/
		return "hello";
	}
}



