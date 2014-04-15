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

	//private final String institution="VUB";
	
	/**
	 * Fetch a certain Floor from the database
	 * @param floor The identifying floor for this object
	 * @param building The building name where the floor supposedly resides
	 * @return Returns the floor object
	 * @throws FloorNotFoundException Exception thrown when the Floor cannot be found in the database
	 */
	public Floor findFloorByFloor(int floor, String building, String institution) throws FloorNotFoundException{
		Floor f = floorRepository.getFloor(floor, building, institution);
		if (f == null)
			throw new FloorNotFoundException("Could not find floor " + floor + " in building " + building + " in institution " + institution);
		return f;
	}

	/**
	 * Fetch a certain Floor from the database and initializes the Rooms.
	 * @param floor The identifying floor for this object
	 * @param building The building name where the floor supposedly resides
	 * @return Returns the floor object
	 * @throws FloorNotFoundException Exception thrown when the Floor cannot be found in the database
	 */
	@Transactional
	public Floor getFloorInitialized(int floor, String building, String institution) throws FloorNotFoundException{
		Floor f = floorRepository.getFloor(floor, building, institution);
		if (f == null)
			throw new FloorNotFoundException("Could not find floor " + floor + " in building " + building + " in institution " + institution);
		f.getRooms().size();
		return f;
	}
	
	/**
	 * Returns a list of all Floor objects which belong to a certain building of a certain institution
	 * @param building The building where all floors should be fetched from
	 * @return Returns a list of all floors in the given building
	 */
	public Set<Floor> getFloorsFromBuilding(String building, String institution) {
		Set<Floor> result = new HashSet<Floor>();
		result.addAll(floorRepository.getFloorsInBuilding(building, institution));
		return result;
	}
	
	
	/**
	 * Create a new floor in the database
	 * @param room	The Floor object to store in the database
	 */
	@Transactional
	public Floor createFloor(Floor floor) {
		return floorRepository.save(floor);
	}
	
	/**
	 * Updates a floor in the database.
	 * @param room	The Floor object to store in the database
	 */
	@Transactional
	public Floor updateFloor(Floor floor) {
		return floorRepository.save(floor);
	}
	
	/**
	 * Remove a Floor from the database
	 * @param floor Floor to remove from the database
	 */
	@Transactional
	public void deleteFloor(Floor floor) {
		floorRepository.delete(floor);
	}
}
