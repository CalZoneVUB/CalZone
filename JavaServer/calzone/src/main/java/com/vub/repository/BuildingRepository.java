package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vub.model.Building;
import com.vub.model.Floor;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
	@Query(value="SELECT b FROM Building b INNER JOIN b.institution i WHERE b.name = :building AND i.name = :institution")
	public Building getBuilding(@Param("building") String building, @Param("institution") String institution);
	
	@Query(value="SELECT b FROM Building b INNER JOIN FETCH b.institution i WHERE b.name = :building AND i.name = :institution")
	public Building getBuildingFetched(@Param("building") String building, @Param("institution") String institution);
}