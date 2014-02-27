package com.vub.model;

import java.util.ArrayList;

public class Subscription {
	ArrayList<Course> courses;
	Student student;
	int academicYear;

	public Subscription(Student s) {
		this.student = s;
		this.courses = new ArrayList<Course>();
		// this.academicYear = //TODO: fetch the year from the db
	}


}
