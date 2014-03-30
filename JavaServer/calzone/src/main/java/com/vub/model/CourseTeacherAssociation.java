package com.vub.model;

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
	private int courseID;
	@Id
	private int teacherID;

	@Column(name="TeachingRole")
	private TeachingRole teachingRole;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="CourseID")
	private Course course;

	@ManyToOne
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
}