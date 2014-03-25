package com.vub.datadump;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vub.model.Course;
import com.vub.model.User;

public class LoadDump {

	public ArrayList<Course> loadCourses() {
		ArrayList<Course> listCourse = new ArrayList<Course>();
		
		
		DbTranslateDump dbTranslateDump = new DbTranslateDump();
		listCourse = dbTranslateDump.loadCourseId();
		//listCourseComponents = dbTranslateDump.loadCourseComponent(8187);
		int i = 0;
		int j = 0;
		
		for (Course course : listCourse) {
			//ArrayList<CourseComponent> listCourseComponents = new ArrayList<CourseComponent>();
			ArrayList<User> listOfProfessors = new ArrayList<User>();
			ArrayList<User> listOfAssistants = new ArrayList<User>();
			
			//listCourseComponents = dbTranslateDump.loadCourseComponent(course.getiD());
			// TODO FIX WITH SERVICES
			/*listOfProfessors = dbTranslateDump.loadProfessor(course.getiD());
			listOfAssistants = dbTranslateDump.loadAssistant(course.getiD());
			
			i = i + listOfAssistants.size();
			j = j + listOfProfessors.size();*/
			
			// TODO - Update met toegevoegde services
			/*course.setListOfAssistants(listOfAssistants);
			course.setListOfProfessors(listOfProfessors);*/
			//course.setListOfComponents(listCourseComponents);
		}
		
		System.out.println("Assistent Size: " + i);
		System.out.println("Proff Size; " + j);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(listCourse));

		return listCourse;
	}
}
