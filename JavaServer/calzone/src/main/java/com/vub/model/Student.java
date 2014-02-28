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
	ArrayList<Subscription> subscriptions;

	public Student(User user) {
		//if(user.getUserTypeName() == UserType.Student) //TODO
			super(user);
	}
	
	public ArrayList<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(ArrayList<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public String toString() {
		return "Student [subscriptions=" + subscriptions + ", toString()="
				+ super.toString() + "]";
	}
	
}