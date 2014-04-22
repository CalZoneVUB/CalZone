package com.vub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vub.model.Faculty;

/**
 * 
 * @author Nicolas
 *
 */
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

}