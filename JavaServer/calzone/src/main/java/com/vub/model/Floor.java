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

import com.vub.model.Building;

/** 
 * Standard class representation of a Floor, which can be used in combination with Building and Room
 * (A room sits on a certain floor, a floor belongs in a certain building, etc)
 * 
 * @author Sam
 *
 */
@Entity
@Table(name="FLOOR")
public class Floor {
	@Id
	@Column(name="FloorID")
	@GeneratedValue
	private int id;
	@Column(name="Floor")
	private int floor;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "BuildingID")
	private Building building;
	
	/**
	 * 
	 * @return	Gets the ID of the floor
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @return Gets the floor number of the floor (e.g. "1" for "first floor")
	 */
	public int getFloor() {
		return floor;
	}
	/**
	 * Set a new floor number for this floor object
	 * "1" equates to first-floor, or ground level "0" is a level lower relative to the entrance.
	 * @param floor	A new floor number
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	/**
	 * 
	 * @return Gets the building this floor is assigned to
	 */
	public Building getBuilding() {
		return building;
	}
}
