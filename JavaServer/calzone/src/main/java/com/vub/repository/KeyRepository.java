package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vub.model.Key;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface KeyRepository extends JpaRepository<Key, String> {

}