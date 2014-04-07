package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vub.repository.RoomRepository;

/**
 * <p>Service class for the Room object. This object provides all of the business logic, which includes:
 * <ul>
 * 	<li>Standard CRUD operations (Create, Read, Update and Delete) </li>
 * 	<li>Listing all the rooms in the database</li>
 * </ul>
 * </p>
 * @author Sam
 *
 */
@Service("buildingService")
public class BuildingService {
	@Autowired
	RoomRepository buildingRepository;
	
}
