package com.vub.model;

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

	@ManyToOne
	@PrimaryKeyJoinColumn(name="CourseID")
	private Course course;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="UserID")
	private User user;
}