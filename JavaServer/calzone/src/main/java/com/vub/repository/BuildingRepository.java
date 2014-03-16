package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vub.model.Building;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

}