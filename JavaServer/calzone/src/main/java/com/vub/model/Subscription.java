/**
 * Subscription class
 * a subscription is a list of courses from a student
 * it also contains the academic year in which the subscription was made
 * 
 * @author youri
 */
package com.vub.model;

import java.util.ArrayList;

public class Subscription {
	ArrayList<Course> courses;
	//Student student; //TODO: necessary?
	int academicYear;

	public Subscription() {
		this.courses = new ArrayList<Course>();
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public int getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	@Override
	public String toString() {
		return "Subscription [courses=" + courses + ", academicYear="
				+ academicYear + "]";
	}

}
