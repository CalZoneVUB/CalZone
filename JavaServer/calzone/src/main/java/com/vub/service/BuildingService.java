package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
	
	
	public Building getBuilding(String building) throws BuildingNotFoundException{
		Building b = buildingRepository.getBuilding(building, institution);
		if (b == null){
			throw new BuildingNotFoundException("Could not find building " + building + " in institution " + institution);
		}
		return b;
	}

	public Building getBuildingInitializedFloors(String building) throws BuildingNotFoundException{	
		try {
			Building b = this.getBuilding(building);
			return b;
		} catch (BuildingNotFoundException ex) {
			throw ex;
		}
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
