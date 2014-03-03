/**
 * 
 * @author pieter, youri
 */

//NOTE: name is deleted since this is available is User
package com.vub.model;

import org.eclipse.jdt.internal.compiler.ast.EqualExpression;

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
		return "Professor [iD=" + iD +", "+ super.toString() +"]";
	}
	

	public boolean equals(Professor prof) {
		//System.out.println("Equals Set Test");
		return prof.getUserName().equals(this.getUserName());
	}
	


}
