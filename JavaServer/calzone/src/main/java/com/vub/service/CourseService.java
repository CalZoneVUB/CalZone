package com.vub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.CourseComponentNotFoundException;
import com.vub.exception.CourseNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.repository.CourseComponentRepository;
import com.vub.repository.CourseRepository;

/**
 * <p>Service for the Course class. This class provides all of the business logic, which includes:
 * <ul>
 * 	<li>Standard CRUD operations (Create, Read, Update and Delete) </li>
 * 	<li>Listing all the courses in the database</li>
 * </ul>
 * </p>
 * @author Sam
 *
 */
@Service("courseService")
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	CourseComponentRepository courseComponentRepository;
	
	/**
	 * Create a new course in the database
	 * @param course The course object to store in the database
	 * @return Returns the Course object. Note that its fields may have been modified as a result of saving it to the database (e.g. primary key may now exist)
	 */
	@Transactional
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	/**
	 * @param course Updates the given course in the database
	 */
	@Transactional
	public Course updateCourse(Course course) {
		return courseRepository.save(course);
	}

	/**
	 * Find a Course object in the database.
	 * @param id	The ID of the Course which needs to be fetched
	 * @return	A Course object fetched from the database
	 * @throws CourseNotFoundException When the Course with the given ID could not be found in the database
	 */
	@Transactional
	public Course findCourseById(int id) throws CourseNotFoundException {
		Course c = courseRepository.findOne(id);
		if(c == null)
			throw new CourseNotFoundException("Could not find Course with ID " + id);
		else return c;
	}
	
	/**
	 * Delete a Course object from the database
	 * @param course	The Course object one wishes to delete
	 */
	@Transactional
	public void deleteCourse(Course course) {
		courseRepository.delete(course);
	}
	
	/**
	 * List all of the courses currently present in the database	
	 * @return	List of Course objects in the database
	 */
	@Transactional
	public List<Course> getCourses() {
		return courseRepository.findAll();
	}
	
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
		else return cc;
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
