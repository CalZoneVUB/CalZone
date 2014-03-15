package com.vub.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vub.model.Room;

/**
 * Database Access Object for the Room class. 
 * Provides basic CRUD database operations.
 * @author Sam
 *
 */
@Repository("roomDAO")
public class RoomDAO {

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * Save a Room object to the database (i.e. make it persistent)
	 * @param room A room object to save in the database
	 */
	public void persistRoom(Room room) {
		sessionFactory.getCurrentSession().persist(room);
	}
	/**
	 * Find a Room in the database
	 * @param id The ID of the room
	 * @return a Room object with all of the values from the database
	 */
	public Room findRoomById(int id) {
		return (Room) sessionFactory.getCurrentSession().get(Room.class, id);
	}
	/**
	 * Update the fields of a given Room object in the database
	 * @param room The room to update in the database
	 */
	public void updateRoom(Room room) {
		sessionFactory.getCurrentSession().update(room);

	}
	/**
	 * Delete a room from the database
	 * @param room The room to delete from the database
	 */
	public void deleteRoom(Room room) {
		sessionFactory.getCurrentSession().delete(room);
	}
}