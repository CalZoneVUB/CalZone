package com.vub.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Sam
 *
 */
@Entity
@Table(name="CourseEnrollmentAssociation")
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
	@PrimaryKeyJoinColumn(name="userID")
	private User user;
}