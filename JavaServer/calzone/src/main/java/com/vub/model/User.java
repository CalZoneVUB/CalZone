package com.vub.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.vub.service.UserService;

/**
 * Standard User representation.
 * @author Tim + Nicolas + Sam
 * 
 */
@Entity
@Table(name="USER")
public class User {
	@Id
	@Column(name="UserID")
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Cannot be empty")
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
	
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="UserRoleID")
	private UserRole userRole;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PersonID")
	@Valid
	private Person person;
	
	@Column(name="Enabled", columnDefinition="BIT", nullable=false)
	private boolean Enabled = false;
	
	@ManyToMany(mappedBy = "teachers", cascade=CascadeType.REMOVE)
	private Set<CourseComponent> teachingCourseComponents = new HashSet<CourseComponent>(0);

	@ManyToMany(mappedBy = "enrolledStudents", cascade=CascadeType.REMOVE)
	private Set<Course> enrolledCourses = new HashSet<Course>(0);
	
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
	
	/**
	 * @return Returns the UserRole assigned to the user
	 */
	public UserRole getUserRole() {
		return userRole;
	}
	/**
	 * Assign a new UseRole to the user.
	 * NOTE: Do not use this method if you just want to assign a new role to a user. 
	 * Use {@link UserService.assignUserRole} instead
	 * @param userRole
	 */
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	/**
	 * Returns a set of CourseComponenst this User is associated with.
	 * The type of the CourseComponent determines the association (for example, if the CourseComponent is a "HOC",
	 * this user must be a professor)
	 * @return Returns a set of course components
	 */
	public Set<CourseComponent> getTeachingCourseComponents() {
		return teachingCourseComponents;
	}
	/**
	 * Sets a set of CourseComponents that this user is associated with.
	 * This is used to define relationships between users and courses (CourseComponents) 
	 * @param newCourseComponents
	 */
	public void setTeachingCourseComponents(Set<CourseComponent> newCourseComponents) {
		this.teachingCourseComponents.addAll(newCourseComponents);
	}
	/**
	 * Returns a set of Courses this User is enrolled for.
	 * @return Returns a set of course objects.
	 */	
	public Set<Course> getEnrolledCourses() {
		return enrolledCourses;
	}
	/**
	 * Sets a set of Courses that this User is enrolled for.
	 * @param courses
	 */
	public void setEnrolledCourses(Set<Course> newEnrolledCourses) {
		this.enrolledCourses.addAll(newEnrolledCourses);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + username + ", password="
				+ password + ", language=" + language + ", userRole="
				+ userRole + ", person=" + person + ", Enabled=" + Enabled
				+ "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
