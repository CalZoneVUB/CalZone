package com.vub.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Class which represents the association between Course and Traject. Every Traject has many courses, and every course can be included with many trajects
 * This class maps that relationship.
 * @author Sam
 *
 */
@Entity
@Table(name="COURSE_TRAJECT_ASSOCIATION")
@IdClass(CourseTrajectAssociationID.class)
public class CourseTrajectAssociation {
	@Id
	private int courseID;
	@Id
	private int trajectID;

	@ManyToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="CourseID")
	private Course course;

	@ManyToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="trajectID")
	private Traject traject;
}