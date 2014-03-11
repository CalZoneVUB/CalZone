package com.vub.model;

import java.sql.Date;

public class Enrollment {
	private int userID;
	private Course course;
	private int academicYear;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}
	
	
}
