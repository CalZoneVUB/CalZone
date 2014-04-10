package com.vub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vub.model.Program;

/**
 * 
 * @author Nicolas
 *
 */
@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {

}