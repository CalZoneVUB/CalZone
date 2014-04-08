package com.vub.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.FloorNotFoundException;
import com.vub.model.Floor;
import com.vub.repository.FloorRepository;


/**
 * @author Nicolas
 *
 */
@Service("floorService")
public class FloorService {
	@Autowired
	FloorRepository floorRepository;

	public Floor getFloor(int floor, String building, String institution) throws FloorNotFoundException{
		Floor f = floorRepository.getFloor(floor, building, institution);
		if (f == null){
			throw new FloorNotFoundException("Could not find floor " + floor + " in building " + building + " in institution " + institution);
		}
		return f;
	}
	
	public Floor getFloorInitialized(int floor, String building, String institution) throws FloorNotFoundException{
		Floor f = floorRepository.getFloorFetched(floor, building, institution);
		if (f == null){
			throw new FloorNotFoundException("Could not find floor " + floor + " in building " + building + " in institution " + institution);
		}
		return f;
	}
	
	
	/**
	 * Create a new floor in the database
	 * @param room	The Floor object to store in the database
	 */
	@Transactional
	public void createFloor(Floor floor) {
		floorRepository.save(floor);
	}
	
}
