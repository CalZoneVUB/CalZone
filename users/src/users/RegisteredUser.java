/**
 * 
 */
package users;

import java.util.Date;

/**
 * @author Youri Coppens
 *
 */
public abstract class RegisteredUser extends User {

	/**
	 * 
	 */
	int rolenumber;
	String firstName, lastName, userName, email, languagePref;
	Byte[] password;
	Date birthDate;
	//TODO Are a getter and setter for birthDate necessary?
	
	/*
	 * dbManagement db;
	 */

	protected RegisteredUser() {
		super();
		//TODO Retrieve the user's data in the db: how to find ans initialize object?
		// db.loadRegisteredUser(...); OR  this.loadRegeisteredUserDB()
	}

	public int getRolenumber() {
		return rolenumber;
	}

	public void setRolenumber(int rolenumber) {
		this.rolenumber = rolenumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLanguagePref() {
		return languagePref;
	}

	public void setLanguagePref(String languagePref) {
		this.languagePref = languagePref;
	}

	public Byte[] getPassword() {
		return password;
	}

	public void setPassword(Byte[] password) {
		this.password = password;
	}
	
	/**
	 * There is a design issue over here: how to respond with the DB?
	 * Wouldn't the DB know how to handle with these objects?
	 */
	public void loadRegisteredUserDB(){
		//db.loadRegisteredUser(...)
	}
	
	public void saveRegisteredUserDB(){
		// db.saveRegisteredUser(this);
	}

}
