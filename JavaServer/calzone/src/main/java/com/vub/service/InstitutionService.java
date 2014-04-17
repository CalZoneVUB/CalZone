package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.InstitutionNotFoundException;
import com.vub.model.Institution;
import com.vub.repository.InstitutionRepository;


/**
 * @author Sam + Nicolas
 *
 */
@Service("institutionService")
public class InstitutionService {
	@Autowired
	InstitutionRepository institutionRepository;

	/**
	 * Find an institution in the database by its given name
	 * @param institution The name of the institution
	 * @return Returns the Institution object
	 * @throws InstitutionNotFoundException When no institution could be found in the database with the given name
	 */
	@Transactional
	public Institution findInstitutionByName(String institution) throws InstitutionNotFoundException{
		Institution i = institutionRepository.getInstitution(institution);
		if (i == null){
			throw new InstitutionNotFoundException("Could not find institution with name " + institution);
		}
		return i;
	}
	
	/**
	 * Create a new institution in the database. Use the returned value for further computation, as it might've changed completely.
	 * @param room	The Institution object to store in the database
	 * @return Returns the Institution object which might've changed as a result of saving it to the database
	 */
	@Transactional
	public Institution createInstitution(Institution institution) {
		return institutionRepository.save(institution);
	}
	
	/**
	 * Remove an Institution from the database
	 * @param institution The institution which is to be removed from the database
	 */
	@Transactional
	public void deleteInstitution(Institution institution) {
		institutionRepository.delete(institution);
	}
}
