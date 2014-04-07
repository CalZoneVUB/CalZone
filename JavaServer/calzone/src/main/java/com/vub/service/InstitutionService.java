package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.InstitutionNotFoundException;
import com.vub.model.Institution;
import com.vub.repository.InstitutionRepository;


/**
 * @author Nicolas
 *
 */
@Service("institutionService")
public class InstitutionService {
	@Autowired
	InstitutionRepository institutionRepository;

	public Institution getInstitution(String institution) throws InstitutionNotFoundException{
		Institution i = institutionRepository.getInstitution(institution);
		if (i == null){
			throw new InstitutionNotFoundException("Could not find institution " + institution);
		}
		return i;
	}
	
	/**
	 * Create a new institution in the database
	 * @param room	The Institution object to store in the database
	 */
	@Transactional
	public void createInstitution(Institution institution) {
		institutionRepository.save(institution);
	}
	
}
