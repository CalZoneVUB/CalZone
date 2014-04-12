package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vub.model.CourseComponent;

/**
 * 
 * @author Nicolas
 *
 */
@Repository
public interface CourseComponentRepository extends JpaRepository<CourseComponent, Integer> {
	
}