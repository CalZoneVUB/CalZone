package com.vub.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.CourseNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.User;
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
	 * @param name	The name of the Course which needs to be fetched
	 * @return	A Course object fetched from the database
	 * @throws CourseNotFoundException When the Course with the given name could not be found in the database
	 */
	@Transactional
	public Course findCourseByName(String name) throws CourseNotFoundException {
		ArrayList<Course> c = courseRepository.getCourse(name);
		if(c == null || c.size() == 0)
			throw new CourseNotFoundException("Could not find Course with name " + name);
		return c.get(0); // GET ONLY FIRST ONE !;
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
		return c;
	}
	/**
	 * Find a Course object in the database and initializes Trajects.
	 * @param id	The ID of the Course which needs to be fetched
	 * @return	A Course object fetched from the database
	 * @throws CourseNotFoundException When the Course with the given ID could not be found in the database
	 */
	@Transactional
	public Course findCourseByIdInitialized(int id) throws CourseNotFoundException {
		Course c = courseRepository.findOne(id);
		if(c == null)
			throw new CourseNotFoundException("Could not find Course with ID " + id);
		c.getCourseComponents().size();
		for(CourseComponent cp: c.getCourseComponents()){
			cp.getTeachers().size();
		}
		c.getTrajects().size();
		return c;
	}
	
	/**
	 * @param id - id of the course
	 * @return - A Course object fetched from the database
	 * @throws - CourseNotFoundException
	 * @author Tim
	 */
	@Transactional
	public Course findCourseByIdInitializedEnrollements(int id) throws CourseNotFoundException {
		Course c = courseRepository.findOne(id);
			c.getEnrolledStudents().size();
		return c;
	} 
	
	/**
	 * Delete a Course object from the database
	 * @param course	The Course object one wishes to delete
	 */
	@Transactional
	public void deleteCourse(Course course) {
		courseRepository.delete(course);
	}
	
	
	@Transactional
	public Set<Course> getCourses() {
		Set<Course> result = new HashSet<Course>();
		result.addAll(courseRepository.findAll());
		return result;
	}
	
	/**
	 * Gets initialized courses from id to to id
	 * @param from - integer of id 
	 * @param to - integer of id
	 * @return - returns Set<Courses>
	 * @author Tim
	 */
	@Transactional
	public Set<Course> getCoursesInitialized(int from , int to) {
		Set<Course> result = new HashSet<Course>();
		result.addAll(courseRepository.getCoursesLimit(from, to));
		
		for (Course c : result) {
			for(CourseComponent cc : c.getCourseComponents()) {
				for (User u : cc.getTeachers()) {
					u.getPerson();
				}
			}
		}

		return result;
	}
	
	@Transactional
	public Set<Course> getCoursesInitialized() {
		Set<Course> result = new HashSet<Course>();
		result.addAll(courseRepository.findAll());
		
		for (Course c : result) {
			for(CourseComponent cc : c.getCourseComponents()) {
				for (User u : cc.getTeachers()) {
					u.getPerson();
				}
			}
		}

		return result;
	}
}
