package com.vub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.vub.validators.ValidUserName;


/**
 * Standard User representation.
 * @author Tim + Nicolas + Sam
 * 
 */
@Entity
@Table(name="USERS_")
public class User {
	@Id
	@Column(name="UserID")
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Cannot be empty")
	@ValidUserName(message = "Username already exists")
	@Column(name="Username")
	private String username;
	
	@NotBlank(message = "Cannot be empty")
	@Size(min = 8, message = "Pick a password greater then 8 characters")
	@Column(name="Password")
	private String password;
	
	@Column(name="Language")
	private String language;
	
	@ManyToOne
	@JoinColumn(name="UserRoleID")
	private UserRole userRole;
	
	@OneToOne
	@JoinColumn(name="PersonID")
	private Person person;
	
	@Column(name="Enabled", columnDefinition="default 0")
	private boolean Enabled;
	
	/**
	 * @return Returns the ID of the user
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return Returns the username of the user
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets a new username for this user
	 * @param userName
	 */
	public void setUsername(String userName) {
		this.username = userName;
	}
	/**
	 * @return Returns the hashed password for this user
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Set a new hashed password for this user
	 * @param password New hashed password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the language preference of this user
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * Sets a new language preference for this user
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return Returns the Person object for this user, which contains additional information
	 */
	public Person getPerson() {
		return person;
	}
	/**
	 * Sets a new Person object for this user
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	/**
	 * @return Check if this user is enabled in the system (i.e. the user is activated)
	 */
	public boolean isEnabled() {
		return Enabled;
	}
	/**
	 * Set whether this user is activated or not
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + username + ", password="
				+ password + ", language=" + language + ", userRole="
				+ userRole + ", person=" + person + ", Enabled=" + Enabled
				+ "]";
	}
}
