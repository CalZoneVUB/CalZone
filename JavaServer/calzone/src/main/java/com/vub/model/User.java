package com.vub.model;

//import java.util.Date;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.vub.dao.EnrollmentDao;
import com.vub.dao.UserDao;
import com.vub.model.UserType.UserTypeEnum;
import com.vub.validators.ValidEmail;
import com.vub.validators.ValidUserName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 
 * @author Tim + Nicolas
 * 
 */
@Entity
@Table(name="Users")
public class User {
	@Id
	@Column(name="UserID")
	@GeneratedValue
	private int id;
	@NotBlank(message = "Cannot be empty")
	@ValidUserName(message = "Username already exists")
	@Column(name="UserName")
	private String userName;
	@NotBlank(message = "Cannot be empty")
	@Size(min = 8, message = "Pick a password greater then 8 characters")
	@Column(name="Password")
	private String password;
	@Column(name="Language")
	private String language;
	// USERTYPE ???????
	@ManyToOne
	@JoinColumn(name="UserTypeID")
	private UserType userType;
	@OneToOne
	@JoinColumn(name="PersonID")
	private Person person;
	@Column(name="Enabled")
	private boolean Enabled;
	
	private ArrayList<Enrollment> listOfEnrollments;

	public User() {
		this.language="NL";
		this.userType = new UserType(UserTypeEnum.ROLE_STUDENT);
		Date date = new Date(2000, 1, 1);
		this.person.setBirthdate(date);
	}

	/**
	 * Creates an instance of an user. Leaves the listOfEnrollments initially
	 * empty, if asked this will be fetched from the database.
	 * 
	 * @param userID
	 * @param userName
	 * @param password
	 * @param language
	 * @param type
	 * @param lastName
	 * @param firstName
	 * @param email
	 * @param birthdate
	 */

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Gets the list of enrollments. Fetches the necessary information from
	 * the database if it is not already loaded in the user object.
	 * 
	 * @return list of enrollments
	 */
	public ArrayList<Enrollment> getListOfEnrollments() {
		if (listOfEnrollments == null) {
			this.listOfEnrollments = new EnrollmentDao().getEnrollments(this);
		}
		return listOfEnrollments;
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

	/**
	 * Enrollment object is added to listOfEnrollments of the user.
	 * At the same time, it is inserted in the database
	 * @param enrollment
	 */
	public void addEnrolledCourse(Enrollment enrollment) {
		this.getListOfEnrollments().add(enrollment);
		EnrollmentDao enrollmentDao = new EnrollmentDao();
		enrollmentDao.insertEnrollment(this, enrollment);
		enrollmentDao.closeDao();
	}
	
	/**
	 * Enrollment object is removed from listOfEnrollments of the user.
	 * At the same time, it is removed in the database
	 * @param enrollment
	 */
	public void deleteEnrolledCourse(Enrollment enrollment) {
		this.getListOfEnrollments().remove(enrollment);
		EnrollmentDao enrollmentDao = new EnrollmentDao();
		enrollmentDao.deleteEnrollment(this, enrollment);
		enrollmentDao.closeDao();
	}
}
