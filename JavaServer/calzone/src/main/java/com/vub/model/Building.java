package com.vub.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vub.model.Institution;
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
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "InstitutionID")
	private Institution institution;

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
}
