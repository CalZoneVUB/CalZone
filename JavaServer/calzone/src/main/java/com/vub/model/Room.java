package com.vub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vub.model.Floor;

/** 
 * Implements the standard room object.
 * 
 * @author Sam
 *
 */

// Tell Hibernate that this class represents an object that we can persist.
@Entity
// Tell Hibernate to which table the class properties should be mapped
@Table(name="Rooms")
public class Room {
	// Id annotation says this field is the primary key
	// GeneratedValue annotation says that this value will be determined by the datasource, not by the code.
	// Column annotation is used to map this property to the RoomID column in the Rooms table.
	@Id
	@Column(name="RoomID")
	@GeneratedValue
	private long id;
	
	@Column(name="Room")
	private String name;
	
	@Column(name="Capacity")
	private int capacity;
	
	@Column(name="RoomType")
	@Enumerated(EnumType.STRING)
	private RoomType type;
	
	@Column(name="HasProjector")
	private boolean hasProjector;
	
	@Column(name="HasRecorder")
	private boolean hasRecorder;
	
	@Column(name="HasSmartBoard")
	private boolean hasSmartBoard;
	
	@ManyToOne
	@JoinColumn(name = "FloorID")
	private Floor floor;
	
	
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
	public long getId() {
		return id;
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
	 * 
	 * @return Whether the room is equipped with a projector or not
	 */
	public boolean isProjectorEquipped() {
		return hasProjector;
	}
	/**
	 * 
	 * @param hasProjector Set whether the room is equipped with a projector or not
	 */
	public void setProjectorEquipped(boolean hasProjector) {
		this.hasProjector = hasProjector;
	}
	
	/**
	 * 
	 * @return	Check if the room is equipped with recording equipment
	 */
	public boolean isRecorderEquipped() {
		return hasRecorder;
	}
	/**
	 * 
	 * @param hasRecorder Set whether the room is equipped with recording equipment or not
	 */
	public void setRecorderEquipped(boolean hasRecorder) {
		this.hasRecorder = hasRecorder;
	}
	
	/**
	 * 
	 * @return Check if the room is equipped with a Smart Board
	 */
	public boolean isSmartBoardEquipped() {
		return hasSmartBoard;
	}
	
	/**
	 * 
	 * @param hasSmartBoard	Set whether the room is equipped with a Smart Board or not
	 */
	public void setSmartBoardEquipped(boolean hasSmartBoard) {
		this.hasSmartBoard = hasSmartBoard;
	}
	/**
	 * Return the display name assigned to the object. Note that this is the actual display name, 
	 * which may not be defined. Use @link {@link com.vub.service.RoomService#getRoomVUBNotation(Room)}
	 * to get the way in which rooms should be displayed to the user. 
	 * 
	 * @return the display naem assigned to this room (may return empty string if undefined)
	 */
	public String getDisplayName() {
		return "";
	}
	
	/**
	 * Get the floor to which this room object belongs
	 * @return the Floor object this room beongs to
	 */
	public Floor getFloor() {
		return floor;
	}
	
	@Override
	public String toString() {
		return "Room [ID=" + id + ", name=" + name + ", capacity=" + capacity + ", type=" + type.toString()
				+ ", hasProjector=" + hasProjector + ", hasSmartBoard=" + hasSmartBoard +", hasRecorder=" + hasRecorder;
	}
	
	
	
	
	/** @deprecated Use isProjectorEquipped() */
	@Deprecated
	public boolean isHasProjector() {
		return hasProjector;
	}
	/** @deprecated Use setProjectorEquipped() */
	@Deprecated
	public void setHasProjector(boolean hasProjector) {
		this.hasProjector = hasProjector;
	}
	/** @deprecated Use isRecorderEquipped() */
	@Deprecated
	public boolean isHasRecorder() {
		return hasRecorder;
	}
	/** @deprecated Use setRecorderEquipped() */
	@Deprecated
	public void setHasRecorder(boolean hasRecorder) {
		this.hasRecorder = hasRecorder;
	}
	/** @deprecated Use isSmartBoardEquipped() */
	@Deprecated
	public boolean isHasSmartBoard() {
		return hasSmartBoard;
	}
	/** @deprecated Use setSmartBoardEquipped() */
	@Deprecated
	public void setHasSmartBoard(boolean hasSmartBoard) {
		this.hasSmartBoard = hasSmartBoard;
	}
	/** @deprecated Use getCapacity() */
	@Deprecated
	public int getNumber() {
		return capacity;
	}
	/** @deprecated Use setCapacity() */
	@Deprecated
	public void setNumber(int number) {
		this.capacity = number;
	}
}