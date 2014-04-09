package com.vub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Association object which resides between a CourseComponent and a Teacher (User). 
 * This association keeps both the User, the Teacher, and possible data which is
 * unique in every relationship (e.g. the role - assistant or professor)
 * 
 * @author Sam, Nicolas
 *
 */
@Entity
@Table(name="COURSE_TEACHER_ASSOCIATION")
@IdClass(CourseTeacherAssociationID.class)
public class CourseTeacherAssociation {

	/**
	 * Constructor that makes an empty association.
	 */
	public CourseTeacherAssociation(){
		
	}
	
	/**
	 * Constructor that makes an association between courseComponent and user and initializes both ID fields.
	 * @param courseComponent is the component from the course that is teached.
	 * @param user is the teacher that teaches the courseComponent.
	 * @param teachingRole is an enumeration of TeachingRole.
	 */
	public CourseTeacherAssociation(CourseComponent courseComponent, User user, TeachingRole teachingRole) {
		super();
		this.courseComponentID = courseComponent.getId();
		this.teacherID = user.getId();
		this.teachingRole = teachingRole;
		this.courseComponent = courseComponent;
		this.user = user;
	}
	@Id
	private int courseComponentID;
	@Id
	private int teacherID;

	@Column(name="TeachingRole")
	private TeachingRole teachingRole;

	@ManyToOne()
	@PrimaryKeyJoinColumn(name="CourseComponentID")
	private CourseComponent courseComponent;

	@ManyToOne()
	@PrimaryKeyJoinColumn(name="TeacherID", referencedColumnName="UserID")
	private User user;
	
	/**
	 * Every role the teacher can take in a relation between a courseComponent and a user (the teacher). 
	 * @author Sam
	 *
	 */
	public static enum TeachingRole {
		Assistant, Professor
	}
	
	public int getCourseComponentID() {
		return courseComponentID;
	}
	public void setCourseComponentID(int courseComponentID) {
		this.courseComponentID = courseComponentID;
	}
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
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
	/**
	 * @return Returns the role the teacher has in this association
	 */
	public TeachingRole getTeachingRole() {
		return teachingRole;
	}
	/**
	 * Sets the role this teacher has in the association
	 * @param teachingRole The new role
	 */
	public void setTeachingRole(TeachingRole teachingRole) {
		this.teachingRole = teachingRole;
	}
	/**
	 * 
	 * @return Returns the CourseComponent in this Course-Teacher association (i.e. the CourseComponent this teacher belongs to)
	 */ 
	public CourseComponent getCourseComponent() {
		return courseComponent;
	}
	/**
	 * Set the CourseComponent in this Course-Teacher association (which CourseComponent should be association with the teacher in this association)
	 * @param courseComponent The new CourseComponent
	 */
	public void setCourseComponent(CourseComponent courseComponent) {
		this.courseComponent = courseComponent;
	}
}