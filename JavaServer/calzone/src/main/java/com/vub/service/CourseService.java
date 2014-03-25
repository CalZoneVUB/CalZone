package com.vub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.model.Course;
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
	
	/**
	 * Create a new course in the database
	 * @param course The course object to store in the database
	 */
	@Transactional
	public void createCourse(Course course) {
		courseRepository.save(course);
	}

	/**
	 * @param course Updates the given course in the database
	 */
	@Transactional
	public void updateCourse(Course course) {
		courseRepository.save(course);
	}

	/**
	 * Find a Course object in the database.
	 * @param id	The ID of the Course which needs to be fetched
	 * @return	A Course object fetched from the database
	 */
	@Transactional
	public Course findCourseById(int id) {
		return courseRepository.findOne(id);
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
}
