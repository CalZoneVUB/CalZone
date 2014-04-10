package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 * Save (persist) a courseComponent in the database
	 * @param courseComponent
	 */
	@Transactional
	public CourseComponent updateCourseComponent(CourseComponent courseComponent) {
		return courseComponentRepository.save(courseComponent);
	}
}
