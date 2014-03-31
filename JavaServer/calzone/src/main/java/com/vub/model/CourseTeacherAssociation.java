package com.vub.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Association object which resides between a Course and a Teacher (User). 
 * This association keeps both the User, the Teacher, and possible data which is
 * unique in every relationship (e.g. the role - assistant or professor)
 * 
 * @author Sam
 *
 */
@Entity
@Table(name="COURSE_TEACHER_ASSOCIATION")
@IdClass(CourseTeacherAssociationID.class)
public class CourseTeacherAssociation {
	@Id
	private int courseComponentID;
	@Id
	private int teacherID;

	@Column(name="TeachingRole")
	private TeachingRole teachingRole;

	@ManyToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="CourseComponentID")
	private CourseComponent courseComponent;

	@ManyToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="TeacherID", referencedColumnName="UserID")
	private User user;

	/**
	 * Every role the teacher can take in a relation between a course and a user (the teacher). 
	 * @author Sam
	 *
	 */
	public static enum TeachingRole {
		Assistant, Professor
	}
	
	/**
	 * Get the user from the association 
	 * @return Returns the User object
	 */
	public User getUser() {
		return user;
	}
	/**
	 * Set the user in the association
	 * @param user User to add to the association
	 */
	public void setUser(User user) {
		this.user = user;
	}
}