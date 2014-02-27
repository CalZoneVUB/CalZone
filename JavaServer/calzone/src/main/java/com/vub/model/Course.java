package com.vub.model;

import java.util.ArrayList;

public class Course {
	int iD;
	String description;
	Career career; //Bachelor,Master ...
	ArrayList<CourseComponent> listOfComponents; //WPO HOC EX
	ArrayList<Professor> listOfProfessors;
	//ArrayList<Assistant> listOfAssistant;
	int typicallyOffered; //Semester 1/2 or year / 2 year course
	//Faculty
	//Institution
	
	public Course() {
		listOfComponents = new ArrayList<CourseComponent>();
		listOfProfessors = new ArrayList<Professor>();
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
	
	public int getTypicallyOffered() {
		return typicallyOffered;
	}
	
	public void setTypicallyOffered(int typicallyOffered) {
		this.typicallyOffered = typicallyOffered;
	}
}
