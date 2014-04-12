package com.vub.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Class which represents the association between Course and User. Every Course has many users, and every user has many courses. 
 * This class maps that relationship.
 * @author Sam
 *
 */
@Entity
@Table(name="COURSE_ENROLLMENT_ASSOCIATION")
@IdClass(CourseEnrollmentAssociationID.class)
public class CourseEnrollmentAssociation {
	@Id
	private int courseID;
	@Id
	private int userID;

	@ManyToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="CourseID")
	private Course course;

	@ManyToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="UserID")
	private User user;

	/**
	 * @return Returns the courseID of the association.
	 */
	public int getCourseID() {
		return courseID;
	}

	/**
	 * @author youri
	 * @param Sets the courseID from the association.
	 */
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	/**
	 * @author youri
	 * @return Returns the userID of the association. 
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @author youri
	 * @param Sets the userID from the association.
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @author youri
	 * @return Returns the course of the association.
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @author youri
	 * @param Sets the course from the association.
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @author youri
	 * @return Returns the user from the association.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @author youri
	 * @param Sets the user from the association.
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}