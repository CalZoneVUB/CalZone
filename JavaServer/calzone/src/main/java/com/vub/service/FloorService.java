package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
