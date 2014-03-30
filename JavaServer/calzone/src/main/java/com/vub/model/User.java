package com.vub.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="USER")
public class User {
	@Id
	@Column(name="userID")
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Cannot be empty")
	// TODO - Fix validation (throws error when submitting form: http://codepad.org/Wm9zJinj)
	//@ValidUserName(message = "Username already exists")
	@Column(name="Username")
	private String username;
	
	@NotBlank(message = "Cannot be empty")
	@Size(min = 8, message = "Pick a password greater then 8 characters")
	@Column(name="Password")
	private String password;
	
	@Column(name="Language")
	@Enumerated(EnumType.STRING)
	private SupportedLanguage language = SupportedLanguage.EN_UK;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="UserRoleID")
	private UserRole userRole;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PersonID")
	private Person person;
	
	@Column(name="Enabled", columnDefinition="BIT", nullable=false)
	private boolean Enabled = false;
	
	/**
	 * Enumeration of all supported languages in the system
	 * <ul>
	 * 	<li>EN_UK: English (United Kingdom)</li>
	 * 	<li>NL_BE: Dutch (Belgium)</li>
	 * </ul>
	 * @author Sam
	 *
	 */
	public static enum SupportedLanguage {
		EN_UK, NL_BE
	}
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
	public SupportedLanguage getLanguage() {
		return language;
	}
	/**
	 * Sets a new language preference for this user
	 * @param language
	 */
	public void setLanguage(SupportedLanguage language) {
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
