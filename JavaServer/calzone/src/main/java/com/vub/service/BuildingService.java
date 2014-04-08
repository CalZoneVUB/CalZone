package com.vub.service;

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

	public Building getBuilding(String building, String institution) throws BuildingNotFoundException{
		Building b = buildingRepository.getBuilding(building, institution);
		if (b == null){
			throw new BuildingNotFoundException("Could not find building " + building + " in institution " + institution);
		}
		return b;
	}

	public Building getBuildingInitialized(String building, String institution) throws BuildingNotFoundException{
		Building b = buildingRepository.getBuildingFetched(building, institution);
		if (b == null){
			throw new BuildingNotFoundException("Could not find building " + building + " in institution " + institution);
		}
		return b;
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
