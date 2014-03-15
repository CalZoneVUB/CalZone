package com.vub.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vub.model.Floor;

/**
 * Database Access Object for the Floor class. 
 * Provides basic CRUD database operations.
 * @author Sam
 *
 */
@Repository("floorDAO")
public class FloorDAO {

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * Save a Floor object to the database (i.e. make it persistent)
	 * @param floor a floor object to save in the database
	 */
	public void persistFloor(Floor floor) {
		sessionFactory.getCurrentSession().persist(floor);
	}
	/**
	 * Find a Floor in the database
	 * @param id The ID of the floor
	 * @return a Floor object with all of the values from the database
	 */
	public Floor findFloorById(int id) {
		return (Floor) sessionFactory.getCurrentSession().get(Floor.class, id);
	}
	/**
	 * Update the fields of a given Floor object in the database
	 * @param floor The floor to update in the database
	 */
	public void updateFloor(Floor floor) {
		sessionFactory.getCurrentSession().update(floor);

	}
	/**
	 * Delete a floor from the database
	 * @param floor The floor to delete from the database
	 */
	public void deleteFloor(Floor floor) {
		sessionFactory.getCurrentSession().delete(floor);
	}
}