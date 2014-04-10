package com.vub.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class represents a traject, or a collection of courses which can be applied to one year (e.g. the first year in a bachelors degree of computer science)
 * The "standard traject" would include all the mandatory courses. Thus, a traject is a collection of courses.
 * 
 * @author Sam
 *
 */
@Entity
@Table(name="TRAJECT")
public class Traject {
	@Id
	@GeneratedValue
	@Column(name="TrajectID")
	private int id;

	@Column(name="TrajectName")
	private String trajectName;

	@Column(name="AcademicYear")
	private int startingYear;

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="ProgramID")
	private Program program;

	//@OneToMany(mappedBy="traject", cascade=CascadeType.ALL)
	//private List<CourseTrajectAssociation> courses;
	
	@ManyToMany()
	@JoinTable(name = "TrajectCourses", joinColumns = { 
			@JoinColumn(name = "TrajectID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "CourseID", 
					nullable = false, updatable = false) })
	private List<Course> courses;

	/**
	 * @return Returns the name of this traject
	 */
	public String getTrajectName() {
		return trajectName;
	}
	/**
	 * Set the new name for this Traject
	 * @param trajectName new name for this traject
	 */
	public void setTrajectName(String trajectName) {
		this.trajectName = trajectName;
	}
	/**
	 * @return Returns the first year of this traject (academic year can be derived; e.g. 2012-2013 has starting year 2012)
	 */
	public int getStartingYear() {
		return startingYear;
	}
	/**
	 * Set a new starting year for this traject.
	 * The starting year is the first year of the academic year, e.g. in 2012-2013, 2012 would be the starting year.
	 * @param startingYear New starting year
	 */
	public void setStartingYear(int startingYear) {
		this.startingYear = startingYear;
	}
	/**
	 * @return Returns the program this traject belongs to.
	 */
	public Program getProgram() {
		return program;
	}
	/**
	 * Set the program this traject belongs to.
	 * @param program
	 */
	public void setProgram(Program program) {
		this.program = program;
	}
	/**
	 * @return Returns the list of courses in this Traject
	 */
	public List<Course> getCourses() {
		return courses;
	}
	/**
	 * Set the list of courses in this traject
	 * @param courses New list of courses
	 */
	public void setCourses(List<Course> courses) {
		// TODO - IMPLEMENT?
	}

	/**
	 * @return Returns the unique identifier for this traject
	 */
	public int getId() {
		return id;
	}

}
