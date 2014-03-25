package com.vub.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Class which represents the association between Course and Program. Every Program has many courses, and every course can be included with many programs. 
 * This class maps that relationship.
 * @author Sam
 *
 */
@Entity
@Table(name="COURSE_PROGRAM_ASSOCIATION")
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