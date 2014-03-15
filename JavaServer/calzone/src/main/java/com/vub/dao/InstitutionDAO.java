package com.vub.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vub.model.Institution;

/**
 * Database Access Object for the Institution class. 
 * Provides basic CRUD database operations.
 * @author Sam
 *
 */
@Repository("institutionDAO")
public class InstitutionDAO {

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * Save an Institution object to the database (i.e. make it persistent)
	 * @param institution An institution object to save in the database
	 */
	public void persistInstitution(Institution institution) {
		sessionFactory.getCurrentSession().persist(institution);
	}
	/**
	 * Find an Institution in the database
	 * @param id The ID of the institution
	 * @return an Institution object with all of the values from the database
	 */
	public Institution findInstitutionById(int id) {
		return (Institution) sessionFactory.getCurrentSession().get(Institution.class, id);
	}
	/**
	 * Update the fields of a given Institution object in the database
	 * @param institution The institution to update in the database
	 */
	public void updateInstitution(Institution institution) {
		sessionFactory.getCurrentSession().update(institution);

	}
	/**
	 * Delete an institution from the database
	 * @param institution The institution to delete from the database
	 */
	public void deleteInstitution(Institution institution) {
		sessionFactory.getCurrentSession().delete(institution);
	}
}