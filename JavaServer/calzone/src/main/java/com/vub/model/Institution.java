package com.vub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * Standard model for an institution.
 * @author Sam
 *
 */
@Entity
@Table(name="Institutions")
public class Institution {
	@Id
	@Column(name="InstitutionID")
	@GeneratedValue
	private int id;
	@Column(name="InstitutionName")
	private String name;

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
	/**
	 * 
	 * @return Gets the id of the institution
	 */
	public int getId() {
		return id;
	}
}