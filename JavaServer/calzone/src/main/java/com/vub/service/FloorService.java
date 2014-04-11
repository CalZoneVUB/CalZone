package com.vub.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.FloorNotFoundException;
import com.vub.model.Floor;
import com.vub.repository.FloorRepository;


@Service("floorService")
public class FloorService {
	@Autowired
	FloorRepository floorRepository;

	private final String institution="VUB";
	
	/**
	 * Fetch a certain Floor from the database
	 * @param floor The identifying floor for this object
	 * @param building The building name where the floor supposedly resides
	 * @return Returns the floor object
	 * @throws FloorNotFoundException Exception thrown when the Floor cannot be found in the database
	 */
	public Floor getFloor(int floor, String building) throws FloorNotFoundException{
		Floor f = floorRepository.getFloor(floor, building, institution);
		if (f == null){
			throw new FloorNotFoundException("Could not find floor " + floor + " in building " + building + " in institution " + institution);
		}
		return f;
	}
	
	/**
	 * Returns a list of all Floor objects which belong to a certain building of a certain institution
	 * @param building The building where all floors should be fetched from
	 * @return Returns a list of all floors in the given building
	 */
	public Set<Floor> getFloorsFromBuilding(String building) {
		Set<Floor> result = new HashSet<Floor>();
		result.addAll(floorRepository.getFloorsInBuilding(building, institution));
		return result;
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
