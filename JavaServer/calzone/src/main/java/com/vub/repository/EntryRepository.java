package com.vub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vub.model.Entry;

/**
 * 
 * @author Nicolas
 *
 */
@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {

}