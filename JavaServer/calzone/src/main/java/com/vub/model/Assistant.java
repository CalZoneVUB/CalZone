/**
 * @author youri
 */

package com.vub.model;

public class Assistant extends User  {

	public Assistant(User user) {
		// if(user.getUserName() == UserType.Assistant) //TODO
		super(user);
	}
	public boolean equals(Professor prof) {
		return prof.getUserName().equals(this.getUserName());
	}
	
	
}
