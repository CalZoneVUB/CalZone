package com.vub.model;

import java.util.HashSet;
import java.util.Set;

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "floor", cascade=CascadeType.ALL, orphanRemoval=true)
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
	public void setRooms(Set<Room> newRooms) {
		this.rooms.addAll(newRooms);
	}
	
	@Override
	public String toString() {
		return "Floor [id=" + id + ", floor=" + floor + ", building="
				+ building + "]";
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
		Floor other = (Floor) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
