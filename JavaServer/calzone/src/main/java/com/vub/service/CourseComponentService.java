package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.CourseComponentNotFoundException;
import com.vub.model.CourseComponent;
import com.vub.repository.CourseComponentRepository;

/**
 * 
 * @author Nicolas
 *
 */
@Service("courseComponentService")
public class CourseComponentService {
	@Autowired
	CourseComponentRepository courseComponentRepository;
	
	/**
	 * Find a certain CourseComponent by its ID.
	 * Why does this exists? Because sometimes from a user-interface perspective, when you need to update values in CourseComponent,
	 * you can only pass the primary key of the CourseComponent as the identifier (can't pass the Course ID, because it has many CourseComponents)
	 * So in this case, this method allows you to find a CourseComponent by its ID and another method allows you to update it.
	 * @param id The ID of the CourseComponent, not the course
	 * @return Returns a CourseComponent object
	 * @throws CourseComponentNotFoundException When no CourseComponent with the given ID could be found in the database
	 */
	@Transactional
	public CourseComponent findCourseComponentById(int id) throws CourseComponentNotFoundException {
		CourseComponent cc = courseComponentRepository.findOne(id);
		if(cc == null)
			throw new CourseComponentNotFoundException("Could not find CourseComponent with ID " + id);
		cc.getTeachers().size(); //Needed to fetch the teachers in the open Transaction.
		return cc;
	}
	
	/**
	 * Save a CourseComponent in the database. 
	 * When you have a reference to only a CourseComponent (not its course), and you still want to save it to the database, 
	 * use this method. If you do have a reference to its associated Course, just save the course instead!
	 * @param courseComponent The CourseComponent to be saved in the database
	 */
	public void updateCourseComponent(CourseComponent courseComponent) {
		courseComponentRepository.save(courseComponent);
	}
}
