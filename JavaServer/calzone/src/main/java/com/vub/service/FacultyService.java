package com.vub.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.model.Faculty;
import com.vub.repository.FacultyRepository;

/**
 * 
 * @author Nicolas
 *
 */
@Service("facultyService")
public class FacultyService {
	@Autowired
	FacultyRepository facultyRepository;
	
	/**
	 * Create (persist) a faculty in the database
	 * @param faculty
	 */
	@Transactional
	public Faculty createFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}
	
	/**
	 * Update (persist) a faculty in the database
	 * @param faculty
	 */
	@Transactional
	public Faculty updateFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}
	

	/**
	 * Find a Faculty object in the database.
	 * @param id	The ID of the Faculty which needs to be fetched
	 * @return	A Faculty object fetched from the database
	 */
	@Transactional
	public Faculty findFacultyById(int id) {
		return facultyRepository.findOne(id);
	}

	/**
	 * Delete a Faculty object from the database
	 * @param faculty	The Faculty object one wishes to delete
	 */
	@Transactional
	public void deleteFaculty(Faculty faculty) {
		facultyRepository.delete(faculty);
	}
	
	/**
	 * List all of the faculties currently present in the database	
	 * @return	List of Faculty objects in the database
	 */
	@Transactional
	public Set<Faculty> getFacultys() {
		Set<Faculty> result = new HashSet<Faculty>();
		result.addAll(facultyRepository.findAll());
		return result;
	}
}
