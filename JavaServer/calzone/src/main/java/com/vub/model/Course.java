package com.vub.model;

import java.util.ArrayList;

public class Course {
	int iD;
	String description;
	Career career; //Bachelor,Master ...  
	ArrayList<CourseComponent> listOfComponents; //WPO HOC EX
	ArrayList<User> listOfProfessors;
	ArrayList<User> listOfAssistants;
	TypicallyOffered typicallyOffered; //Semester 1/2 or year / 2 year course
	//Faculty //TODO: for 3rd iteration
	//Institution //TODO: for 3rd iteration
	
	public Course() {
		this.listOfComponents = new ArrayList<CourseComponent>();
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
	
	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public ArrayList<CourseComponent> getListOfComponents() {
		return listOfComponents;
	}

	public void setListOfComponents(ArrayList<CourseComponent> listOfComponents) {
		this.listOfComponents = listOfComponents;
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

	public void setListOfAssistants(ArrayList<User> listOfAssistants) {
		this.listOfAssistants = listOfAssistants;
	}

	public TypicallyOffered getTypicallyOffered() {
		return typicallyOffered;
	}

	public void setTypicallyOffered(TypicallyOffered typicallyOffered) {
		this.typicallyOffered = typicallyOffered;
	}

	@Override
	public String toString() {
		return "Course [iD=" + iD + ", description=" + description
				+ ", career=" + career + ", listOfComponents="
				+ listOfComponents + ", listOfProfessors=" + listOfProfessors
				+ ", listOfAssistants=" + listOfAssistants
				+ ", typicallyOffered=" + typicallyOffered + "]";
	}
	
}
