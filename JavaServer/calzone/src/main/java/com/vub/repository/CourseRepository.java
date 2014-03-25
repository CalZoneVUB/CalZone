package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vub.model.Course;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}