package com.vub.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** 
 * Standard model for an institution.
 * @author Sam
 *
 */
@Entity 
@Table(name="INSTITUTION")
public class Institution {
	@Id
	@Column(name="InstitutionID")
	@GeneratedValue
	private int id;
	
	@Column(name="InstitutionName")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institution")
	private Set<Building> buildings = new HashSet<Building>(0);
	
//	/**
//	 * A list of all faculties associated with this institution
//	 */
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institution")
//	private Set<Faculty> faculties = new HashSet<Faculty>(0);


	/** 
	 *
	 * @return  Gets the institution name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name Sets the institution name to the given string
	 */
	public void setName(String name) {
		this.name = name;
	}
//	/**
//	 * 
//	 * @return Returns a list of all faculties associated with this institution
//	 */
//	public List<Faculty> getFaculties() {
//		return this.faculties;
//	}
	/**
	 * 
	 * @return Gets the id of the institution
	 */
	public int getId() {
		return id;
	}
	/**
	 * Gets the buildings of this institution.
	 * @return set of Building objects.
	 */
	public Set<Building> getBuildings() {
		return this.buildings;
	}
	/**
	 * Sets the buildings of this institution.
	 * @param buildings set of Building objects.
	 */
	public void setBuildings(Set<Building> buildings) {
		this.buildings = buildings;
	}

	
	@Override
	public String toString() {
		return "Institution [id=" + id + ", name=" + name + ", buildings="
				+ buildings + "]";
	}
	
	@Override
	/**
	 * Check if an institution is equal to another institution. Two institutions are said to be equal if their ID matches.
	 */
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Institution))return false;
	    Institution otherInstitution = (Institution)other;
	    return this.getId() == otherInstitution.getId();
	}
}