package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vub.model.Floor;
import com.vub.model.Room;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	
}