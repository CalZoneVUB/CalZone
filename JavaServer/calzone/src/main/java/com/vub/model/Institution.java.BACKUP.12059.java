package com.vub.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
	
<<<<<<< HEAD
//	/**
//	 * A list of all faculties associated with this institution
//	 */
//	@OneToMany(mappedBy="institution", cascade=CascadeType.ALL)
//	private List<Faculty> faculties;
=======
	/**
	 * A list of all faculties associated with this institution
	 */
	// TODO - Add lazy fetching
	@OneToMany(mappedBy="institution")
	private List<Faculty> faculties;
	
	// TODO - Lazy fetching
	@OneToMany(mappedBy="institution")
	private List<Building> buildings;
>>>>>>> fbc75baccabbd39717cbe9c097016a62685ca44c

	/**
	 * 
	 * @return Returns the list of buildings which are present in this institution
	 */
	public List<Building> getBuildings() {
		return buildings;
	}
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
	 * @param id Sets a new id for this institution
	 */
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Institution [id=" + id + ", name=" + name ;//+ ", faculties="
//				+ faculties + "]";
	}
	
	
}