package com.vub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vub.model.Course;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}