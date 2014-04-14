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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "InstitutionID")
	private Institution institution;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "building")
	private Set<Floor> floors = new HashSet<Floor>(0);
	
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
	 * Gets the floors of this building.
	 * @return set of Floor objects.
	 */
	public Set<Floor> getFloors() {
		return this.floors;
	}
	/**
	 * Sets the floors of this building.
	 * @param floors set of Floor objects.
	 */
	public void setFloors(Set<Floor> floors) {
		this.floors = floors;
	}
	
	@Override
	public String toString() {
		return "Building [id=" + id + ", name=" + name + ", institution="
				+ institution + "]";
	}
	
	@Override
	/**
	 * Check if a building is equal to another building. Two buildings are said to be equal if their ID matches,
	 * and their associated Institution matches.
	 */
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Building))return false;
	    Building otherBuilding = (Building)other;
	    return (this.getId() == otherBuilding.getId() &&
	    		this.institution.equals(otherBuilding.getInstitution()));
	}
}
