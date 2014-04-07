package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vub.model.Floor;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {
	@Query(value="SELECT f FROM Floor f INNER JOIN f.building b INNER JOIN b.institution i WHERE f.floor = :floor AND b.name = :building AND i.name = :institution")
	public Floor getFloor(@Param("floor") int floor, @Param("building") String building, @Param("institution") String institution);

	@Query(value="SELECT f FROM Floor f INNER JOIN FETCH f.building b INNER JOIN FETCH b.institution i WHERE f.floor = :floor AND b.name = :building AND i.name = :institution")
	public Floor getFloorFetched(@Param("floor") int floor, @Param("building") String building, @Param("institution") String institution);
}