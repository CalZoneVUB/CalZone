package com.vub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vub.model.Course;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	@Query(value="SELECT c FROM Course c JOIN FETCH c.trajects, c.courseComponents WHERE c.id = :courseID")
	public Course findOneInitialized(@Param("courseID") int id);

}