package com.vub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.model.Traject;
import com.vub.repository.TrajectRepository;

/**
 * 
 * @author Nicolas
 *
 */
@Service("trajectService")
public class TrajectService {
	@Autowired
	TrajectRepository trajectRepository;
	
	/**
	 * Create (persist) a traject in the database
	 * @param traject
	 */
	@Transactional
	public Traject createTraject(Traject traject) {
		return trajectRepository.save(traject);
	}
	
	/**
	 * Update (persist) a traject in the database
	 * @param traject
	 */
	@Transactional
	public Traject updateTraject(Traject traject) {
		return trajectRepository.save(traject);
	}
	

	/**
	 * Find a Traject object in the database.
	 * @param id	The ID of the Traject which needs to be fetched
	 * @return	A Traject object fetched from the database
	 */
	@Transactional
	public Traject findTrajectById(int id) {
		return trajectRepository.findOne(id);
	}

	/**
	 * Delete a Traject object from the database
	 * @param traject	The Traject object one wishes to delete
	 */
	@Transactional
	public void deleteTraject(Traject traject) {
		trajectRepository.delete(traject);
	}
	
	/**
	 * List all of the trajects currently present in the database	
	 * @return	List of Traject objects in the database
	 */
	@Transactional
	public List<Traject> getTrajects() {
		return trajectRepository.findAll();
	}
}
