package com.vub.datadump;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.exception.CourseNotFoundException;
import com.vub.exception.InstitutionNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Faculty;
import com.vub.model.Institution;
import com.vub.model.Program;
import com.vub.model.Traject;
import com.vub.model.User;
import com.vub.service.CourseComponentService;
import com.vub.service.CourseService;
import com.vub.service.InstitutionService;
import com.vub.service.TrajectService;

public class LoadDump {

	public void loadTrajects(){
		
		DbTranslateDump dbTranslateDump = new DbTranslateDump();
		
		Course course;
		ArrayList<String> listCourseNames = new ArrayList<String>();
		ArrayList<Traject> listTraject = new ArrayList<Traject>();
		ArrayList<Program> listProgram = new ArrayList<Program>();
		ArrayList<Faculty> listFaculty = new ArrayList<Faculty>();
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		TrajectService trajectService = (TrajectService) context.getBean("trajectService");
		InstitutionService institutionService = (InstitutionService) context.getBean("institutionService");

		int ctr = 0;
		String institutionName = "VUB";
		try {
			Institution institution = institutionService.findInstitutionByName(institutionName);
			
			listFaculty = dbTranslateDump.loadFaculties(institution);
			
			for(Faculty faculty:listFaculty){
				listProgram = dbTranslateDump.loadProgramsOfFaculty(faculty);
				for(Program program:listProgram){
					listTraject = dbTranslateDump.loadTrajectsOfProgram(program);
					for(Traject traject:listTraject){
						listCourseNames = dbTranslateDump.loadCourseNamesOfTraject(traject);
						for(String courseName:listCourseNames){
							try {
								course = courseService.findCourseByName(courseName);
								traject.getCourses().add(course);
								System.out.println("TrajectCourse CTR = " + ++ctr + ", NAME = " + courseName);
							} catch (CourseNotFoundException e) {
								// TODO Auto-generated catch block
								System.out.println("TrajectCourse NAME = " + courseName + " -> NOT FOUND !");
								// DO NOTHING
							}
						}
						trajectService.updateTraject(traject);
					}
				}
			}
			System.out.println("\nTrajectCourse TOTAL SUCCESSFUL IMPORTED COURSES = " + ctr + "\n");
			context.close();
		} catch (InstitutionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			context.close();
		}
	}
	
	public ArrayList<Course> loadCourses() {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		CourseComponentService courseComponentService = (CourseComponentService) context.getBean("courseComponentService");
		
		ArrayList<Course> listCourse = new ArrayList<Course>();
		
		DbTranslateDump dbTranslateDump = new DbTranslateDump();
		listCourse = dbTranslateDump.loadCourseId();
		
		int ctr = 0;
		int studiedeel;
		System.out.println("***** SIZE COURSES = " + listCourse.size());
		
		for (Course course : listCourse) {
			System.out.println("++ ctr " + ++ctr);
			//if (++ctr > 50) break;
			studiedeel = course.getStudiedeel(); // temp save because when course is saved in and returned from database 'studiedeel' is erased
			Set<CourseComponent> listCourseComponents = new HashSet<CourseComponent>(0);
			Set<User> listOfProfessors = new HashSet<User>(0);
			Set<User> listOfAssistants = new HashSet<User>(0);
			
			course = courseService.createCourse(course);
			listCourseComponents = dbTranslateDump.loadCourseComponent(course);
			
			if(listCourseComponents.size() == 0){
				courseService.deleteCourse(course);
			} else {
				course.setCourseComponents(listCourseComponents);
				course = courseService.updateCourse(course);
				course.setStudiedeel(studiedeel); // restore studiedeel, will be needed by loadProfessor and loadAssistant

				listOfProfessors = dbTranslateDump.loadProfessor(course);
				listOfAssistants = dbTranslateDump.loadAssistant(course);

				for (CourseComponent courseComponent : course.getCourseComponents()){
					if(courseComponent.getType() == CourseComponent.CourseComponentType.HOC){
						courseComponent.setTeachers(listOfProfessors);
						courseComponentService.updateCourseComponent(courseComponent);
					} else if (courseComponent.getType() == CourseComponent.CourseComponentType.WPO){
						courseComponent.setTeachers(listOfAssistants);
						courseComponentService.updateCourseComponent(courseComponent);
					}
				}
			}
		}
		context.close();
		return listCourse;
	}
}
