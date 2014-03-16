package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

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
	 */
	@Transactional
	public Room findRoomById(long id) {
		return roomRepository.findOne(id);
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
	public List<Room> getRooms() {
		return roomRepository.findAll();
	}
}
