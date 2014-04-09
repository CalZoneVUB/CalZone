package com.vub.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/** 
 * Standard building representation. Typically used in combination with Floor and Institution.
 * (An institution has many buildings, and every building has floors, etc)
 * 
 * @author Sam
 *
 */
@Entity
@Table(name="BUILDING")
public class Building {
	@Id
	@Column(name="BuildingID")
	@GeneratedValue
	private long id;
	@Column(name="BuildingName")
	private String name;
	
	@ManyToOne()//, cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "InstitutionID")
	private Institution institution;
	
	@OneToMany(mappedBy="building")
	private List<Floor> floors;

	/**
	 * 
	 * @return 	Gets the name of the building
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name 	Sets the name of the building
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return	Get the institution associated with the building
	 */
	public Institution getInstitution() {
		return institution;
	}
	/**
	 * 
	 * @param institution 	Set a new Institution object for this building
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	/**
	 * 
	 * @return	Gets the ID of the building
	 */
	public long getId() {
		return id;
	}
	/** 
	 * @param id Sets a new id for this building
	 */
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Building [id=" + id + ", name=" + name + ", institution="
				+ institution + "]";
	}
	
	
}
