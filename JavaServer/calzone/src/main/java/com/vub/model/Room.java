package com.vub.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonView;

import com.vub.model.Floor;
import com.vub.utility.Views;

/** 
 * Implements the standard room object.
 * 
 * @author Sam
 *
 */

// Tell Hibernate that this class represents an object that we can persist.
@Entity
// Tell Hibernate to which table the class properties should be mapped
@Table(name="ROOM")
public class Room {
	// Id annotation says this field is the primary key
	// GeneratedValue annotation says that this value will be determined by the datasource, not by the code.
	// Column annotation is used to map this property to the RoomID column in the Rooms table.
	@Id
	@Column(name="RoomID")
	@GeneratedValue
	private int id;
	
	@Column(name="Room")
	private String name;
	
	@Column(name="Capacity")
	private int capacity;
	
	@Column(name="RoomType")
	@Enumerated(EnumType.STRING)
	private RoomType type;

	@Column(name="HasProjector")
	private boolean projectorEquipped;
	
	@Column(name="HasRecorder")
	private boolean recorderEquipped;
	
	@Column(name="HasSmartBoard")
	private boolean smartBoardEquipped;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FloorID")
	private Floor floor;
	
	@Column(name="DisplayName")
	private String displayName;
	
	@OneToMany(mappedBy="room", cascade=CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
	@JsonIgnore
	private Set<Entry> entries = new HashSet<Entry>(0);
	
	/**
	 * Enumerates the different types a Room can take, which is either a classroom or a computerroom
	 * @author Sam
	 *
	 */
	public static enum RoomType {
		ComputerRoom, ClassRoom
	}

	/** 
	 * 
	 * @return  The ID of the room
	 */
	@JsonView({Views.EntryFilter.class, Views.RoomSelectFilter.class})
	public int getId() {
		return id;
	}
	
	/**
	 * This method should only be used for the creation of correct test data. 
	 * For real data, the id is automatically created by hibernate.
	 * 
	 * @param id the id for the object.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** 
	 * @return Get the type of the room
	 */
	public RoomType getType() {
		return type;
	}

	/**
	 * 
	 * @param type Sets an new type for this room
	 */
	public void setType(RoomType type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The name of the room
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name	Sets a new name for this room
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return Whether the room is equipped with a projector or not
	 */
	public boolean isProjectorEquipped() {
		return projectorEquipped;
	}
	/**
	 * 
	 * @param hasProjector Set whether the room is equipped with a projector or not
	 */
	public void setProjectorEquipped(boolean hasProjector) {
		this.projectorEquipped = hasProjector;
	}
	/**
	 * 
	 * @return Check if the room is equipped with recording equipment
	 */
	public boolean isRecorderEquipped() {
		return recorderEquipped;
	}
	/**
	 * 
	 * @param hasRecorder Set whether the room is equipped with recording equipment or not
	 */
	public void setRecorderEquipped(boolean hasRecorder) {
		this.recorderEquipped = hasRecorder;
	}
	/**
	 * 
	 * @return Check if the room is equipped with a Smart Board
	 */
	public boolean isSmartBoardEquipped() {
		return smartBoardEquipped;
	}
	/**
	 * 
	 * @param hasSmartBoard Set whether the room is equipped with a Smart Board or not
	 */
	public void setSmartBoardEquipped(boolean hasSmartBoard) {
		this.smartBoardEquipped = hasSmartBoard;
	}
	
	/**
	 * 
	 * @return Get the capacity of the room
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * 
	 * @param capacity	Set a new capacity for the room
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Return the display name assigned to the object. Note that this is the actual display name, 
	 * which may not be defined. Use @link {@link com.vub.service.RoomService#getRoomVUBNotation(Room)}
	 * to get the way in which rooms should be displayed to the user. 
	 * 
	 * @return the display naem assigned to this room (may return empty string if undefined)
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * 
	 * @param displayName Sets the display name of the room 
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * Get the floor to which this room object belongs
	 * @return the Floor object this room beongs to
	 */
	public Floor getFloor() {
		return floor;
	}
	
	/**
	 * 
	 * @param floor Set floor to which this room object belongs
	 */
	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	
	/**
	 * @return the entries
	 */
	public Set<Entry> getEntries() {
		return entries;
	}

	/**
	 * @param entries the entries to set
	 */
	public void setEntries(Set<Entry> newEntries) {
		this.entries.clear();
		this.entries.addAll(newEntries);
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", capacity=" + capacity
				+ ", type=" + type + ", hasProjector=" + projectorEquipped
				+ ", hasRecorder=" + recorderEquipped + ", hasSmartBoard="
				+ smartBoardEquipped + ", floor=" + floor + ", displayName="
				+ displayName + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	@JsonView({Views.EntryFilter.class, Views.RoomSelectFilter.class})
	public String getVubNotation () {
		if (displayName == null) {
			String name = "";
			name += floor.getBuilding().getName();
			name += ".";
			name += floor.getFloor();
			name += ".";
			name += this.getName();
			return name;
		} else {
			return displayName;
		}
	}
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
		Room other = (Room) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
