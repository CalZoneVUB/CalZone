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
@Table(name="CourseProgramAssociation")
@IdClass(CourseProgramAssociationID.class)
public class CourseProgramAssociation {
	@Id
	private int courseID;
	@Id
	private int programID;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="CourseID")
	private Course course;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="programID")
	private Program program;
}