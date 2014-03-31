package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vub.model.Floor;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {

}