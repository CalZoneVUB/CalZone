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
	
	@OneToMany(mappedBy="program", cascade=CascadeType.ALL)
	private List<CourseProgramAssociation> courses;
}
