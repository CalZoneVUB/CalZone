/**
 * Student class
 * Extends the User class
 * A student has a list of subscriptions 
 * @author youri
 *
 */
package com.vub.model;

import java.util.ArrayList;

public class Student extends User {
	ArrayList<Course> courses;

	public Student(User user) {
		//if(user.getUserTypeName() == UserType.Student) //TODO
			super(user);
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [courses=" + courses + "]";
	}
	
	
	
}