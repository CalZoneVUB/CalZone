package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vub.model.Institution;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

}