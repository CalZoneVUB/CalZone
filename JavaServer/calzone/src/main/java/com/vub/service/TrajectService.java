package com.vub.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Traject;
import com.vub.model.User;
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
	 * Find a Traject object in the database fully initialized
	 * @param id	The ID of the Traject which needs to be fetched
	 * @return	A Traject object fetched from the database
	 */
	@Transactional
	public Traject findTrajectByIdInitialized(int id) {
		Traject traject = trajectRepository.findOne(id);
		traject.getCourses().size();
		return traject;
	}
	
	@Transactional
	public Traject findTrajectByIdInitializedFull(int id) {
		Traject traject = trajectRepository.findOne(id);
		for (Course course : traject.getCourses()) {
			for (CourseComponent component: course.getCourseComponents()) {
				for (User user : component.getTeachers()) {
					user.getPerson();
				}
				course.getTrajects().size();
			}
		}
		return traject;
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
	public Set<Traject> getTrajects() {
		Set<Traject> result = new HashSet<Traject>();
		result.addAll(trajectRepository.findAll());
		return result;
	}
	
	/**
	 * @return a set of initialised trajects
	 */
	@Transactional
	public Set<Traject> getTrajectsInitialized() {
		Set<Traject> result = new HashSet<Traject>();
		result.addAll(trajectRepository.findAll());
		Set<Traject> res = new HashSet<Traject>();
		for (Traject t: result) {
			Traject tr = findTrajectByIdInitializedFull(t.getId());
			res.add(tr);
		}
		return result;
	}
}
