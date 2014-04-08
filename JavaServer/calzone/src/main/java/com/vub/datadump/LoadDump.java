package com.vub.datadump;

import java.util.ArrayList;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.CourseTeacherAssociation;
import com.vub.model.CourseTeacherAssociation.TeachingRole;
import com.vub.model.User;
import com.vub.service.CourseService;
import com.vub.service.CourseTeacherAssociationService;
import com.vub.service.UserService;

public class LoadDump {

	public ArrayList<Course> loadCourses() {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		CourseTeacherAssociationService courseTeacherAssociationService = (CourseTeacherAssociationService) context.getBean("courseTeacherAssociationService");
		
		
		ArrayList<Course> listCourse = new ArrayList<Course>();
		
		
		DbTranslateDump dbTranslateDump = new DbTranslateDump();
		listCourse = dbTranslateDump.loadCourseId();
		int i = 0;
		int j = 0;
		
		int ctr = 0;
		
		for (Course course : listCourse) {
			if (++ctr > 10) break;
			ArrayList<CourseComponent> listCourseComponents = new ArrayList<CourseComponent>();
			ArrayList<User> listOfProfessors = new ArrayList<User>();
			ArrayList<User> listOfAssistants = new ArrayList<User>();
			
			listCourseComponents = dbTranslateDump.loadCourseComponent(course.getStudiedeel());
			
			course.setCourseComponents(listCourseComponents);
			
			course = courseService.createCourse(course);
			
			listOfProfessors = dbTranslateDump.loadProfessor(course.getStudiedeel());
			listOfAssistants = dbTranslateDump.loadAssistant(course.getStudiedeel());
			
			for (CourseComponent courseComponent : course.getCourseComponents()){
				if(courseComponent.getType() == CourseComponent.CourseComponentType.HOC){
					for(User u : listOfProfessors){
						CourseTeacherAssociation courseTeacherAssociation = new CourseTeacherAssociation();
						courseTeacherAssociation.setCourseComponentID(courseComponent.getId());
						courseTeacherAssociation.setTeacherID(u.getId());
						courseTeacherAssociation.setCourseComponent(courseComponent);
						courseTeacherAssociation.setUser(u);
						courseTeacherAssociation.setTeachingRole(TeachingRole.Professor);
						courseTeacherAssociation = courseTeacherAssociationService.createCourseTeacherAssociation(courseTeacherAssociation);
					}
				} else if (courseComponent.getType() == CourseComponent.CourseComponentType.WPO){
					for(User u : listOfAssistants){
						CourseTeacherAssociation courseTeacherAssociation = new CourseTeacherAssociation();
						courseTeacherAssociation.setCourseComponentID(courseComponent.getId());
						courseTeacherAssociation.setTeacherID(u.getId());
						courseTeacherAssociation.setCourseComponent(courseComponent);
						courseTeacherAssociation.setUser(u);
						courseTeacherAssociation.setTeachingRole(TeachingRole.Assistant);
						courseTeacherAssociation = courseTeacherAssociationService.createCourseTeacherAssociation(courseTeacherAssociation);
					}
				}
			}
		}
		
//		System.out.println("Assistent Size: " + i);
//		System.out.println("Proff Size; " + j);
//		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		System.out.println(gson.toJson(listsCourse));

		return listCourse;
	}
}
