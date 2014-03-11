package com.vub.model;

//import java.util.Date;

import java.sql.Date;
import java.util.ArrayList;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.vub.validators.ValidEmail;
import com.vub.validators.ValidUserName;

/**
 * 
 * @author Tim
 *
 */
public class User {
	private int userID;
	@NotBlank(message="Cannot be empty")
	@ValidUserName(message = "Username already exists")
	private String userName;
	@NotBlank(message="Cannot be empty")
	@Size(min=8, message = "Pick a password greater then 8 characters")
	private String password;
	private String language;
	private UserType type;
	@NotBlank(message="Cannot be empty")
	private String lastName;
	@NotBlank(message="Cannot be empty")
	private String firstName;
	@NotBlank(message="Cannot be empty")
	@Email(message = "Not a real email adress")
	@ValidEmail(message = "Email already exist.")
	private String email;
	private Date birthdate;
	private ArrayList<Enrollment> listOfEnrollments;



	

	public User() {
		setLanguage("NL");
		setType(UserType.ROLE_STUDENT);
		Date date = new Date(2000,1,1);
		setBirthdate(date);
	}

	public User(int userID, String userName, String password, String language,
			UserType type, String lastName, String firstName, String email,
			Date birthdate) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.language = language;
		this.type = type;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.birthdate = birthdate;
	}
	
	public User(String userName) {
		setUserName(userName);
		if (Globals.DEBUG == 1) 
			System.out.println("Created User with: " + userName);
	}
	
	// Copy constructor
	// Needed in constructors of subclasses to initialize the superclass
	public User(User user) {
		this.userID = user.getUserID();
		this.birthdate = user.getBirthdate();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.language = user.getLanguage();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.userName = user.getUserName();
		this.type = user.getType();
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public ArrayList<Enrollment> getListOfEnrollments() {
		return listOfEnrollments;
	}

	public void setListOfEnrollments(ArrayList<Enrollment> listOfEnrollments) {
		this.listOfEnrollments = listOfEnrollments;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	/** DEPRECATED. Use getType() */
	public String getUserTypeName() {
		return type.toString();
	}
	
	/** DEPRECATED. Use setType() */
	public void setUserTypeName(String userTypeName) {
		this.type = UserType.valueOf(userTypeName);
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName
				+ ", password=" + password + ", language=" + language
				+ ", type=" + type + ", lastName=" + lastName + ", firstName="
				+ firstName + ", email=" + email + ", birthdate=" + birthdate
				+ "]";
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return ((User) obj).getUserName().equals(this.getUserName());
	}
	
	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * this.getUserName().hashCode();
		return hash;
	}

}
