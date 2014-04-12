package com.vub.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.BuildingNotFoundException;
import com.vub.model.Building;
import com.vub.repository.BuildingRepository;


/**
 * @author Nicolas
 *
 */
@Service("buildingService")
@Transactional
public class BuildingService {
	@Autowired
	BuildingRepository buildingRepository;
	
	/**
	 * Retrieve a certain Building from the database
	 * @param building The name of the building you wish to fetch
	 * @param institution The Institution the building belongs to
	 * @return Returns the Building object with data from the database
	 * @throws BuildingNotFoundException When no building could be found with the given name
	 */
	@Transactional
	public Building findBuildingByName(String building, String institution) throws BuildingNotFoundException{
		Building b = buildingRepository.getBuilding(building, institution);
		if (b == null){
			throw new BuildingNotFoundException("Could not find building " + building + " in institution " + institution);
		}
		return b;
	}
	
	/**
	 * 	
	 * @return Returns a list of all buildings in the database
	 */
	@Transactional
	public Set<Building> getAllBuildings() {
		Set<Building> result = new HashSet<Building>();
		result.addAll(buildingRepository.findAll());
		return result;
	}
	
	/**
	 * Create a new building in the database
	 * @param room	The Building object to store in the database
	 * @return The building which is the result of the saving to the database - use this to continue computation
	 */
	@Transactional
	public Building createBuilding(Building building) {
		return buildingRepository.save(building);
	}
	
	/**
	 * Remove a Building from the database
	 * @param building Building to remove from the DB
	 */
	@Transactional
	public void deleteBuilding(Building building) {
		buildingRepository.delete(building);
	}
}
