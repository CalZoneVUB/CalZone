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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BuildingID")
	private Building building;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "floor")
	private Set<Room> rooms = new HashSet<Room>(0);
	
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
	/**
	 * 
	 * @param floor Set floor to which this room object belongs
	 */
	public void setBuilding(Building building) {
		this.building = building;
	}
	/**
	 * Gets the rooms of this floor.
	 * @return set of Room objects.
	 */
	public Set<Room> getRooms() {
		return this.rooms;
	}
	/**
	 * Sets the rooms of this floor.
	 * @param rooms set of Room objects.
	 */
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
	
	@Override
	public String toString() {
		return "Floor [id=" + id + ", floor=" + floor + ", building="
				+ building + "]";
	}
	
	@Override
	/**
	 * Check if a floor is equal to another floor. Two floors are said to be equal if their ID matches,
	 * and if their associated building matches.
	 */
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Floor))return false;
	    Floor otherFloor = (Floor)other;
	    return (this.getId() == otherFloor.getId() &&
	    		this.building.equals(otherFloor.getBuilding()));
	}
}
