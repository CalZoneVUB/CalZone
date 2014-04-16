package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vub.model.Room;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	
}