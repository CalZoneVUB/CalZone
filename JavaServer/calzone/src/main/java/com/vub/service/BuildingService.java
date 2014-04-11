package com.vub.service;

import java.util.List;

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
public class BuildingService {
	@Autowired
	BuildingRepository buildingRepository;
	
	/**
	 * Don't support multiple institutions in the service methods, because they're a fucking pain in the ass
	 * So just assume every building will be a VUB building
	 */
	private final String institution="VUB";
	
	
	/**
	 * Retrieve a certain building from the database
	 * @param building The name of the building you wish to fetch
	 * @return Returns the building object with data from the database
	 * @throws BuildingNotFoundException When no building could be found with the given name
	 */
	public Building getBuilding(String building) throws BuildingNotFoundException{
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
	public List<Building> getAllBuildings() {
		return buildingRepository.findAll();
	}
	
	/**
	 * Create a new building in the database
	 * @param room	The Building object to store in the database
	 */
	@Transactional
	public void createBuilding(Building building) {
		buildingRepository.save(building);
	}
	
}
