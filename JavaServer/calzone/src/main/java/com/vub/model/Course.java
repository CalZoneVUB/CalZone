package com.vub.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Class represents a Course.
 * @author Sam
 *
 */
@Entity
@Table(name="COURSE")
public class Course {
	@Id
	@GeneratedValue
	@Column(name="CourseID")
	private int id;
	
	/**
	 * optional parameter used when importing courses from datadump into database, where their ID = studiedeel.
	 */
	@Transient
	private int studiedeel;
	
	@Column(name="CourseName")
	private String courseName;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "CourseDataID")
	private CourseData courseData;
	
	@OneToMany(mappedBy="course", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<CourseComponent> courseComponents;
	
	// TODO - Remove all the classes associated with this association (it is mapped through course - traject - program)
	//@OneToMany(mappedBy="course", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	//private List<CourseProgramAssociation> programs;
	
	@OneToMany(mappedBy="course", fetch=FetchType.LAZY)
	private List<CourseTrajectAssociation> trajects;
	
	@OneToMany(mappedBy="course", fetch=FetchType.LAZY)
	private List<CourseEnrollmentAssociation> users;

	/**
	 * 
	 * @return Returns the name of the course
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Set the name of the course
	 * @param New name of the course
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * 
	 * @return Gets the data associated with this course
	 */
	public CourseData getCourseData() {
		return courseData;
	}
	/**
	 * Set a new data object for this course
	 * @param courseData New CourseData object
	 */
	public void setCourseData(CourseData courseData) {
		this.courseData = courseData;
	}
	/**
	 * 
	 * @return Returns the courseComponents linked to this course (In Dutch: HOC, WPO, EXAMEN)
	 */
	public List<CourseComponent> getCourseComponents() {
		return courseComponents;
	}
	/**
	 * 
	 * @param courseComponents Sets the course components for this Course
	 */
	public void setCourseComponents(List<CourseComponent> courseComponents) {
		this.courseComponents = courseComponents;
	}
	/** 
	 * @return Returns the list of users who are associated with this course
	 */
	public List<CourseEnrollmentAssociation> getUsers() {
		return users;
	}
	/**
	 * Set the list of users associated with this course
	 * @param users List of users
	 */
	public void setUsers(List<CourseEnrollmentAssociation> users) {
		this.users = users;
	}
	/**
	 * 
	 * @return Returns the ID of the course
	 */
	public int getId() {
		return id;
	}

	public int getStudiedeel() {
		return studiedeel;
	}

	public void setStudiedeel(int studiedeel) {
		this.studiedeel = studiedeel;
	}
}