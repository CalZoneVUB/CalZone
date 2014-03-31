/**
 * THIRD ITERATION
 * 
 */
package com.vub.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class models programs. A program is the entire bachelors degree, or the entire masters degree.
 * In this program, trajects are stored - a traject is one specific year in the program.
 * @author Sam
 *
 */
@Entity
@Table(name="PROGRAM")
public class Program {
	@Id
	@GeneratedValue
	@Column(name="ProgramID")
	int id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "FacultyID")
	private Faculty faculty;
	
	//@OneToMany(mappedBy="program", cascade=CascadeType.ALL)
	//private List<CourseProgramAssociation> courses;
	
	@OneToMany(mappedBy="program", cascade=CascadeType.ALL)
	private List<Traject> trajects;
	
	/**
	 * @return Returns the faculty this program is associated with
	 */
	public Faculty getFaculty() {
		return faculty;
	}
	/**
	 * Set the faculty this program is associated with
	 * @param faculty New faculty
	 */
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	/**
	 * Get the list of Traject objects associated with this program
	 * @return Returns a list of Traject objects
	 */
	public List<Traject> getTrajects() {
		return trajects;
	}
	/**
	 * Set the list of Traject objects associated with this course
	 * @param trajects New list of Trajects
	 */
	public void setTrajects(List<Traject> trajects) {
		this.trajects = trajects;
	}
	/**
	 * @return Returns the unique identifier for this program
	 */
	public int getId() {
		return id;
	}
}
