package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vub.model.CourseComponentUserAssociation;
import com.vub.model.Room;

/**
 * 
 * @author Nicolas
 *
 */
@Repository
public interface CourseTeacherAssociationRepository extends JpaRepository<CourseComponentUserAssociation, Integer> {
	
}