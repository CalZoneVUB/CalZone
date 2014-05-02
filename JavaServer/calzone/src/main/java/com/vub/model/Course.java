package com.vub.model;

import java.util.ArrayList;
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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonView;

import com.vub.utility.Views;

/**
 * Class represents a Course.
 * 
 * @author Sam
 * 
 */
@Entity
@Table(name = "COURSE")
public class Course {
	@Id
	@GeneratedValue
	@Column(name = "CourseID")
	private int id;

	/**
	 * optional parameter used when importing courses from datadump into
	 * database, where their ID = studiedeel.
	 */
	@Transient
	private int studiedeel;

	@Column(name = "CourseName")
	@JsonView(Views.EntryFilter.class)
	private String courseName;

	@Column(name = "Frozen")
	boolean frozen;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CourseDataID")
	private CourseData courseData;

	/**
	 * Sets the list of courseComponents of this Course. If a courseComponent is
	 * removed from the set, with the next update in the database the
	 * relationship with this courseComponent will be deleted from the database.
	 * Also orphanRemoval will delete completely that courseComponent from the
	 * database.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<CourseComponent> courseComponents = new HashSet<CourseComponent>(0);

	@JsonIgnore
	@ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Traject> trajects = new HashSet<Traject>(0);

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "COURSE_USER",
				joinColumns = {@JoinColumn(name = "CourseID", nullable = false, updatable = false)},
				inverseJoinColumns = {@JoinColumn(name = "UserID", nullable = false, updatable = false)})
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
	 * 
	 * @param New
	 *            name of the course
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the frozen
	 */
	public boolean isFrozen() {
		return frozen;
	}

	/**
	 * Also set the necessery frozen variable on his parent, namely Traject.
	 * 
	 * @param frozen
	 *            the frozen to set
	 */
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
		
		// Set frozen variables to entries
		for (CourseComponent cc : this.courseComponents) {
			for (Entry e : cc.getEntries()) {
				e.setFrozen(frozen);
			}
		}
		
		
		// Set frozen variables to trajects
	}
	
	/**
	 * Method for notifying Course object that entries are 
	 */
	public void updateFrozen() {
		this.frozen = frozen;
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
	 * 
	 * @param courseData
	 *            New CourseData object
	 */
	public void setCourseData(CourseData courseData) {
		this.courseData = courseData;
	}
	/**
	 * 
	 * @return Returns the courseComponents linked to this course (In Dutch:
	 *         HOC, WPO, EXAMEN)
	 */
	public Set<CourseComponent> getCourseComponents() {
		return courseComponents;
	}
	/**
	 * 
	 * @param courseComponents
	 *            Sets the course components for this Course
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

	/**
	 * This method should only be used for the creation of correct test data.
	 * For real data, the id is automatically created by hibernate.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public int getStudiedeel() {
		return studiedeel;
	}

	public void setStudiedeel(int studiedeel) {
		this.studiedeel = studiedeel;
	}
	/**
	 * Returns a set of the Trajects the course belongs to.
	 * 
	 * @return The list of Trajects
	 */
	public Set<Traject> getTrajects() {
		return trajects;
	}

	/**
	 * Sets the Trajects this Course is a part of.
	 * 
	 * @param newTrajects
	 *            the trajects to set
	 */
	public void setTrajects(Set<Traject> newTrajects) {
		this.trajects.addAll(newTrajects);
	}

	/**
	 * Returns a set of the Students who are enrolled for this Course.
	 * 
	 * @return the enrolledStudents
	 */
	public Set<User> getEnrolledStudents() {
		return enrolledStudents;
	}

	/**
	 * Sets the enrolled Students of this Course.
	 * 
	 * @param newEnrolledStudents
	 *            the enrolledStudents to set
	 */
	public void setEnrolledStudents(Set<User> newEnrolledStudents) {
		this.enrolledStudents.addAll(newEnrolledStudents);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", studiedeel=" + studiedeel
				+ ", courseName=" + courseName + ", courseData=" + courseData
				// + ", courseComponents=" + courseComponents + ", trajects="
				// + trajects + ", enrolledStudents=" + enrolledStudents
				+ "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@JsonIgnore
	public List<User> getListOfProfessors() {
		List<User> users = new ArrayList<User>();
		for(CourseComponent courseComponent : courseComponents) {
			for(User u : courseComponent.getTeachers()) {
				users.add(u);
			}
		}
		return users;
	}

}
