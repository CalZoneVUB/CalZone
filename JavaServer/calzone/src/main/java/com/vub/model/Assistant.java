/**
 * @author youri
 */

package com.vub.model;

public class Assistant extends User  {
	int iD;
	
	public Assistant(User user) {
		// if(user.getUserName() == UserType.Assistant) //TODO
		super(user);
	}
	public boolean equals(Object obj) {
		return ((Assistant)obj).getUserName().equals(this.getUserName());
	}
	
	public int hashCode()  {
		return this.getiD();
	}
	
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	
	
	
	
}
