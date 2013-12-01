package users;

import java.util.UUID;

/**
 * http://www.javapractices.com/topic/TopicAction.do?Id=56
 */

/**
 * @author Youri Coppens
 *
 */

public abstract class User {
	private UUID userID;

	protected User(){
		userID = UUID.randomUUID();
	}
	
	public UUID userID(){
		return userID;
	}
	
	protected void userID(UUID id){
		userID = id;
	}
}
