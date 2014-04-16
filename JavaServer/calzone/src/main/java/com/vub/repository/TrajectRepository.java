package com.vub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vub.model.Traject;

/**
 * 
 * @author Nicolas
 *
 */
@Repository
public interface TrajectRepository extends JpaRepository<Traject, Integer> {

}