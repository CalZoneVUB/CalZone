package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.model.CourseComponentUserAssociation;
import com.vub.repository.CourseTeacherAssociationRepository;

/**
 * 
 * @author Nicolas
 *
 */
@Service("courseTeacherAssociationService")
public class CourseTeacherAssociationService {
	@Autowired
	CourseTeacherAssociationRepository courseTeacherAssociationRepository;
	
	/**
	 * Create (persist) a courseTeacherAssociation in the database
	 * @param courseTeacherAssociation
	 */
	@Transactional
	public CourseComponentUserAssociation createCourseTeacherAssociation(CourseComponentUserAssociation courseTeacherAssociation) {
		return courseTeacherAssociationRepository.save(courseTeacherAssociation);
	}
}
