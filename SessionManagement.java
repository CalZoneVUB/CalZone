import java.util.*;
import java.util.Map.Entry;

public class SessionManagement{

	// Test to compile
	public static void main(String []args){		
		//Object creation
		SessionManagement session = new SessionManagement();
		//session.popMap();
		session.loginUser("Timbo920","monkey123");
		session.loginUser("Timbo921","monkey123");
		session.loginUser("Timbo925","monkey123");
		session.loginUser("Timbo925","monkey123");
		//session.printListKey();
		//session.printListTokenUser();
		//session.printListTokenTime();
	}
	//Variabels
	HashMap<Integer,String> listTokenUser = new HashMap<Integer,String>(); // Map containing the token given to browser/user and and user object
	HashMap<Integer,Long> listTokenTime = new HashMap<Integer,Long>();
	Random rng = new Random(); // Random Number Generator

	//Methods
	//Printing listTokenUser to console
	@SuppressWarnings("unused")
	private void printListTokenUser() {
		System.out.println(listTokenUser.values());
	}

	//Printing listTokenTime to console
	@SuppressWarnings("unused")
	private void printListTokenTime() {
		System.out.println(listTokenTime.values());
	}

	//Printing listKey to console
	@SuppressWarnings("unused")
	private void printListKey() {
		System.out.println(listTokenTime.keySet());
	}

	private int tokenNumber() {
		int rnum = rng.nextInt(9999999);
		while (listTokenUser.containsKey(rnum)) {
			rnum = rng.nextInt(9999999);
		}
		return rnum;
	}

	public HashMap<Integer,String> listTokenUser() {
		return listTokenUser;
	}
	
	public HashMap<Integer,Long> listTokenTime() {
		return listTokenTime;
	}
	
	//Testing user login information and creating a object User to put in listTokenUser
	public void loginUser(String userName, String password) {
		if ("Timbo925" == userName){ //passwordManager.check(userName,password)
			//User user = new User(); 
			//user = userDB.load(userName); //load information from database into object
			//Vector vec = new Vector(2);
			Date date = new Date();
			//vec.addElement(new String (userName)); //Add the user to the vector
			//vec.addElement(date.getTime()); // adding current date to the vector
			int token = tokenNumber();
			listTokenUser.put(token, userName);
			listTokenTime.put(token, date.getTime());
			System.out.println("Succesffull login attempt by: " + userName + " with token: " + token + " at time: " + date.getTime());
		}
		else {
			System.out.println("Failed login attempt by " + userName);
		}
	}

	//Removing token + user from listTokenUser
	public boolean logoutUser(String token) {
		if (listTokenUser.remove(token) != null) {
			listTokenTime.remove(token);
			System.out.println("Token " + token + "removed");
			return true;
		}
		System.out.println("Token " + token + " not found in listTokenUser");
		return false;
	}

	//return User corresponding with token
	public void getLoggedInUser(int token) { //TODO return of type User
		if (listTokenUser.containsKey(token)) {
			//return listTokenUser.get(token).elementAt(token));
			System.out.println(listTokenUser.get(token));
		}
		else {
			System.out.println("Token not found. Token: " + token);
		}

	}

	public boolean validateToken(int token) {
		return listTokenUser.containsKey(token);	
	}

	public void cleanSession() {
		long maxSessionTime = 3600000;

		for (Entry<Integer,Long> entry : listTokenTime.entrySet()) {
			int key = entry.getKey();
			long value = entry.getValue();
			Date date = new Date();
			long time = date.getTime() - value;
			if (time > maxSessionTime) { //Remove form my list when login time is over 1 hour
				listTokenUser.remove(key);
				listTokenTime.remove(key);
			}
		}
	}
} 
