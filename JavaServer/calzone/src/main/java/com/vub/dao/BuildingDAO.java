package com.vub.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vub.model.Building;

/**
 * Database Access Object for the Building class. 
 * @author Sam
 *
 */
@Repository("buildingDAO")
public class BuildingDAO {

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * Save a Building object to the database (i.e. make it persistent)
	 * @param building 	A building object to save in the database
	 */
	public void persistInstitution(Building building) {
		sessionFactory.getCurrentSession().persist(building);
	}
	/**
	 * Find a building in the database
	 * @param id The ID of the building
	 * @return 	a Building object
	 */
	public Building findInstitutionById(int id) {
		return (Building) sessionFactory.getCurrentSession().get(Building.class, id);
	}
	/**
	 * Update the fields of a given Building object in the database
	 * @param building The building to update in the database
	 */
	public void updateInstitution(Building building) {
		sessionFactory.getCurrentSession().update(building);

	}
	/**
	 * Delete a building from the database
	 * @param building The building to delete from the database
	 */
	public void deleteInstitution(Building building) {
		sessionFactory.getCurrentSession().delete(building);
	}

}