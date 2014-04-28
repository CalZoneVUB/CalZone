package com.vub.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vub.model.Course;

/**
 * 
 * @author Sam
 * @author Tim
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	@Query(value="SELECT c FROM Course c WHERE c.courseName = :courseName")
	public ArrayList<Course> getCourse(@Param("courseName") String courseName);
	// TODO - 
	@Query(value="SELECT c FROM Course c JOIN FETCH c.trajects WHERE c.id = :courseID")
	public Course findOneInitialized(@Param("courseID") int id);
	/**
	 * @param from - Beginning id 
	 * @param to - Ending id 
	 * @return - Array list of courses from to to id
	 */
	@Query(value="SELECT c FROM Course c where c.id between :from and :to")
	public ArrayList<Course> getCoursesLimit(@Param("from") int from, @Param("to") int to);

}