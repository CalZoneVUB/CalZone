package com.vub.datadump;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.User;
import com.vub.service.CourseComponentService;
import com.vub.service.CourseService;

public class LoadDump {

	public Set<Course> loadCourses() {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		CourseComponentService courseComponentService = (CourseComponentService) context.getBean("courseComponentService");
		
		Set<Course> listCourse = new HashSet<Course>(0);
		
		DbTranslateDump dbTranslateDump = new DbTranslateDump();
		listCourse = dbTranslateDump.loadCourseId();
		
		int ctr = 0;
		int studiedeel;
		
		for (Course course : listCourse) {
			System.out.println("++ ctr " + ctr);
			if (++ctr > 5) break;
			studiedeel = course.getStudiedeel(); // temp save because when course is saved in and returned from database 'studiedeel' is erased
			Set<CourseComponent> listCourseComponents = new HashSet<CourseComponent>(0);
			Set<User> listOfProfessors = new HashSet<User>(0);
			Set<User> listOfAssistants = new HashSet<User>(0);
			
			course = courseService.createCourse(course);
			listCourseComponents = dbTranslateDump.loadCourseComponent(course);
			course.setCourseComponents(listCourseComponents);
			course = courseService.updateCourse(course);
			course.setStudiedeel(studiedeel); // restore studiedeel, will be needed by loadProfessor and loadAssistant
			
			listOfProfessors = dbTranslateDump.loadProfessor(course);
			listOfAssistants = dbTranslateDump.loadAssistant(course);
			
			for (CourseComponent courseComponent : course.getCourseComponents()){
				if(courseComponent.getType() == CourseComponent.CourseComponentType.HOC){
					courseComponent.setTeachers(listOfProfessors);
					courseComponent.setCourse(course);
					courseComponentService.updateCourseComponent(courseComponent);
				} else if (courseComponent.getType() == CourseComponent.CourseComponentType.WPO){
					courseComponent.setTeachers(listOfAssistants);
					courseComponent.setCourse(course);
					courseComponentService.updateCourseComponent(courseComponent);
				}
			}
		}
		context.close();
		return listCourse;
	}
}
