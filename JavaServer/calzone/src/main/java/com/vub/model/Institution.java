package com.vub.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonIgnore
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
		return "Institution [id=" + id + ", name=" + name + "]";
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
		Institution other = (Institution) obj;
		if (id != other.id)
			return false;
		return true;
	}
}