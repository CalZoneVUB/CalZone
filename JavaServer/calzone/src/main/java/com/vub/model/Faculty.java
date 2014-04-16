package com.vub.model;

import java.util.HashSet;
import java.util.Set;

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
 * Standard class representing a faculty
 * @author Sam
 *
 */
@Entity
@Table(name="FACULTY")
public class Faculty {
	@Id
	@GeneratedValue
	@Column(name="FacultyID")
	private int id;

	@Column(name="FacultyName")
	private String facultyName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "InstitutionID")
	private Institution institution;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
	private Set<Program> programs = new HashSet<Program>(0);
	
	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	/**
	 * @return the programs
	 */
	public Set<Program> getPrograms() {
		return programs;
	}

	/**
	 * @param programs the programs to set
	 */
	public void setPrograms(Set<Program> programs) {
		this.programs = programs;
	}

	public int getId() {
		return id;
	}
}
