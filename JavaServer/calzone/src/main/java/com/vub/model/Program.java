package com.vub.model;

import java.util.ArrayList;

public class Program {
	String iD;
	String faculty;
	String department;
	ArrayList<Course> listOfCourses;
	
	public Program() {
		listOfCourses = new ArrayList<Course>();
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
