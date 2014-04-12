package com.vub.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.RoomNotFoundException;
import com.vub.model.Room;
import com.vub.repository.RoomRepository;

/**
 * <p>Service class for the Room object. This object provides all of the business logic, which includes:
 * <ul>
 * 	<li>Standard CRUD operations (Create, Read, Update and Delete) </li>
 * 	<li>Listing all the rooms in the database</li>
 * </ul>
 * </p>
 * @author Sam
 *
 */
@Service("roomService")
public class RoomService {
	@Autowired
	RoomRepository roomRepository;
	
	/**
	 * Create a new room in the database
	 * @param room	The Room object to store in the database
	 */
	@Transactional
	public void createRoom(Room room) {
		roomRepository.save(room);
	}

	/**
	 * Update the given room object in the database
	 * @param room	Room object to update in the database
	 */
	@Transactional
	public void updateRoom(Room room) {
		roomRepository.save(room);
	}

	/**
	 * Find a room object in the database.
	 * @param id	The ID of the room which needs to be fetched
	 * @return	A Room object fetched from the database
	 * @throws RoomNotFoundException When no room could be found with the given ID
	 */
	@Transactional
	public Room findRoomById(int id) throws RoomNotFoundException {
		Room r = roomRepository.findOne(id);
		if(r == null)
			throw new RoomNotFoundException("Could not find room with ID: " + id);
		
		else return r;
	}

	/**
	 * Delete a Room object from the database
	 * @param room	The room object one wishes to delete
	 */
	@Transactional
	public void deleteRoom(Room room) {
		roomRepository.delete(room);
	}
	
	/**
	 * List all of the rooms currently present in the database	
	 * @return	List of Room objects in the database
	 */
	@Transactional
	public Set<Room> getRooms() {
		Set<Room> result = new HashSet<Room>();
		result.addAll(roomRepository.findAll());
		return result;
	}
	
	/**
	 * 
	 * @param room	The room object of which you want to know the building
	 * @return	The name of the building a room belongs to
	 */
	public String getBuildingName(Room room) {
		com.vub.model.Floor f = room.getFloor();
		return f.getBuilding().getName();
	}
	
	/**
	 * The VUB notation is a concatenation of building name, floor name and room name, separated by a dot.
	 * @param room 	The room of which you want the name in VUB notation
	 * @return	Gets the name of the room in VUB notation, or returns the rooms' display name if defined
	 */
	public String getRoomVUBNotation(Room room) {
		if(room.getDisplayName() != null && !room.getDisplayName().isEmpty())
			return room.getDisplayName();
		else
			return this.getBuildingName(room) + "." + room.getFloor().getFloor() + "." + room.getName();	
	}	
}
