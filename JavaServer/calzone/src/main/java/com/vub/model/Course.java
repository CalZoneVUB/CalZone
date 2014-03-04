package com.vub.model;

import java.util.ArrayList;

public class Course {
	int iD;
	String description;
	//TODO ArrayList<CourseComponent> listOfComponents; //WPO HOC EX
	ArrayList<User> listOfProfessors;
	ArrayList<User> listOfAssistants;
	//TypicallyOffered //TODO: for 3rd iteration TypicallyOffered typicallyOffered; //Semester 1/2 or year / 2 year course
	//Faculty //TODO: for 3rd iteration
	//Institution //TODO: for 3rd iteration
	
	public Course() {
		//TODO this.listOfComponents = new ArrayList<CourseComponent>();
		this.listOfProfessors = new ArrayList<User>();
		this.listOfAssistants = new ArrayList<User>();
	}
	
	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<User> getListOfProfessors() {
		return listOfProfessors;
	}

	public void setListOfProfessors(ArrayList<User> listOfProfessors) {
		this.listOfProfessors = listOfProfessors;
	}

	public ArrayList<User> getListOfAssistants() {
		return listOfAssistants;
	}

	public void setListOfAssistants(ArrayList<User> assistants) {
		this.listOfAssistants = assistants;
	}

	@Override
	public String toString() {
		return "Course [iD=" + iD + ", description=" + description
				+ ", listOfProfessors=" + listOfProfessors
				+ ", listOfAssistants=" + listOfAssistants + "]";
	}
	
}
