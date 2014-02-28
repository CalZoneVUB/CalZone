/**
 * 
 * @author pieter, youri
 */

//NOTE: name is deleted since this is available is User
package com.vub.model;

public class Professor extends User {
	int iD;
	
	public Professor(User user) {
		//if(user.getUserTypeName() == UserType.Professor) //TODO
		super(user);
	}
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	@Override
	public String toString() {
		return "Professor [iD=" + iD + "]";
	}

}
