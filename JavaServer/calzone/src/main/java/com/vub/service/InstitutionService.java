package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.dao.InstitutionDAO;
import com.vub.model.Institution;

/**
 * Service class for the Institution object. 
 * 
 * @author Sam
 *
 */
@Service("institutionService")
public class InstitutionService {
	@Autowired
	InstitutionDAO institutionDAO;
	
	@Transactional
	public void persistInstitution(Institution institution) {
		institutionDAO.persistInstitution(institution);
		
	}

	@Transactional
	public void updateInstitution(Institution institution) {
		institutionDAO.updateInstitution(institution);
		
	}

	@Transactional
	public Institution findInstitutionById(int id) {
		return institutionDAO.findInstitutionById(id);
	}

	@Transactional
	public void deleteInstitution(Institution institution) {
		institutionDAO.deleteInstitution(institution);
		
	}
}
