package com.vub.model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Hibernate;


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
	
	@OneToMany(mappedBy="course", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<CourseComponent> courseComponents = new HashSet<CourseComponent>(0);
	
	@ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
	private Set<Traject> trajects = new HashSet<Traject>(0);

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "COURSE_USER", joinColumns = { 
			@JoinColumn(name = "CourseID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "UserID", 
					nullable = false, updatable = false) })
	private Set<User> enrolledStudents = new HashSet<User>(0);

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
	public Set<CourseComponent> getCourseComponents() {
		return courseComponents;
	}
	/**
	 * 
	 * @param courseComponents Sets the course components for this Course
	 */
	public void setCourseComponents(Set<CourseComponent> courseComponents) {
		this.courseComponents = courseComponents;
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
	/**
	 * Returns a set of the Trajects the course belongs to.
	 * @return The list of Trajects 
	 */
	public Set<Traject> getTrajects() {
		return trajects;
	}

	/**
	 * @return the enrolledStudents
	 */
	public Set<User> getEnrolledStudents() {
		return enrolledStudents;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", studiedeel=" + studiedeel
				+ ", courseName=" + courseName + ", courseData=" + courseData
				//+ ", courseComponents=" + courseComponents + ", trajects="
				//+ trajects + ", enrolledStudents=" + enrolledStudents 
				+ "]";
	}
	
	/**
	 * Two courses are equal when their ID is equal, when the CourseData is equal and when the CourseComponents are equal
	 */
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Room))return false;
	    Course otherCourse = (Course)other;
	    return (this.getId() == otherCourse.getId() &&
	    		this.courseData.equals(otherCourse.getCourseData()) && 
	    		this.courseComponents.equals(otherCourse.getCourseComponents()));
	}
}