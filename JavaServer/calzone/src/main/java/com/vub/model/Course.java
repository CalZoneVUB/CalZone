package com.vub.model;

import java.util.ArrayList;

public class Course {
	int iD;
	String description;
	//TODO ArrayList<CourseComponent> listOfComponents; //WPO HOC EX
	ArrayList<Professor> listOfProfessors;
	ArrayList<Assistant> listOfAssistants;
	//TypicallyOffered //TODO: for 3rd iteration TypicallyOffered typicallyOffered; //Semester 1/2 or year / 2 year course
	//Faculty //TODO: for 3rd iteration
	//Institution //TODO: for 3rd iteration
	
	public Course() {
		//TODO this.listOfComponents = new ArrayList<CourseComponent>();
		this.listOfProfessors = new ArrayList<Professor>();
		this.listOfAssistants = new ArrayList<Assistant>();
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

	public ArrayList<Professor> getListOfProfessors() {
		return listOfProfessors;
	}

	public void setListOfProfessors(ArrayList<Professor> listOfProfessors) {
		this.listOfProfessors = listOfProfessors;
	}

	public ArrayList<Assistant> getListOfAssistants() {
		return listOfAssistants;
	}

	public void setListOfAssistants(ArrayList<Assistant> listOfAssistants) {
		this.listOfAssistants = listOfAssistants;
	}

	@Override
	public String toString() {
		return "Course [iD=" + iD + ", description=" + description
				+ ", listOfProfessors=" + listOfProfessors
				+ ", listOfAssistants=" + listOfAssistants + "]";
	}
	
}
