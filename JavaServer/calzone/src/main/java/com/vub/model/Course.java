package com.vub.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


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
	
	@Column(name="CourseName")
	private String courseName;
	
	@OneToOne
	@JoinColumn(name = "CourseDataID")
	private CourseData courseData;
	
	@OneToMany(mappedBy="course", fetch=FetchType.LAZY)
	private List<CourseTeacherAssociation> teachers;
	
	@OneToMany(mappedBy="course", fetch=FetchType.LAZY)
	private List<CourseProgramAssociation> programs;
	
	@OneToMany(mappedBy="course", fetch=FetchType.LAZY)
	private List<CourseEnrollmentAssociation> users;
	
	/**
	 * Gets a list of all the Course-Teacher associations which are associated with this course.
	 * @return List of associations for this course.
	 */
	public List<CourseTeacherAssociation> getTeachers() {
		return teachers;
	}

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
	 * @return Returns the ID of the course
	 */
	public int getiD() {
		return id;
	}
}