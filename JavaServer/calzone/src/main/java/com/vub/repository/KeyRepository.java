package com.vub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vub.model.Key;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface KeyRepository extends JpaRepository<Key, Integer> {
	@Query("SELECT k FROM Key k WHERE k.keyString = :key")
	public Key findKeyByKeyString(String keystring);
	
}