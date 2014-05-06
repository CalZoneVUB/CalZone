/**
 * THIRD ITERATION
 * 
 */
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vub.model.CourseComponent.CourseComponentType;

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
	
	@Column(name="ProgramName")
	private String programName;
	
	@Column(name="Carreer")
	private Carreer carreer;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FacultyID")
	private Faculty faculty;
	
	@OneToMany(mappedBy="program", cascade=CascadeType.ALL)
	private Set<Traject> trajects = new HashSet<Traject>(0);
	
	/**
	 * <p>Enumeration that describes what the carreer of this program is.<br>
	 * <ul>
	 * <li>BA</li>
	 * <li>MA</li>
	 * <li>MNM</lI>
	 * </ul></p>
	 * 
	 * @author Nicolas
	 *
	 */
	public static enum Carreer {
		BA, MA, MNM
	}
	
	/**
	 * @return the carreer
	 */
	public Carreer getCarreer() {
		return carreer;
	}
	/**
	 * @param carreer the carreer to set
	 */
	public void setCarreer(Carreer carreer) {
		this.carreer = carreer;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
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
	public Set<Traject> getTrajects() {
		return trajects;
	}
	/**
	 * Set the list of Traject objects associated with this course
	 * @param trajects New list of Trajects
	 */
	public void setTrajects(List<Traject> newTrajects) {
		this.trajects.addAll(newTrajects);
	}
	public void setTrajects(Set<Traject> setTrajects) {
		this.trajects.addAll(setTrajects);
		
	}
	/**
	 * @return Returns the unique identifier for this program
	 */
	public int getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	/* (non-Javadoc)
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
		Program other = (Program) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
