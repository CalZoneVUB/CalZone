package com.vub.model;

import java.util.ArrayList;

public class Course {
	int iD;
	String description;
	Career career; //Bachelor,Master ...  
	ArrayList<CourseComponent> listOfComponents; //WPO HOC EX
	ArrayList<Professor> listOfProfessors;
	ArrayList<Assistant> listOfAssistants;
	//TypicallyOffered //TODO: for 3rd iteration TypicallyOffered typicallyOffered; //Semester 1/2 or year / 2 year course
	//Faculty //TODO: for 3rd iteration
	//Institution //TODO: for 3rd iteration
	
	public Course() {
		this.listOfComponents = new ArrayList<CourseComponent>();
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
