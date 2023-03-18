package com.springjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springjpa.model.Drone;

public interface DroneRepository extends CrudRepository<Drone, Long>{
	
	public Optional<Drone> findById(Long id);
	public List<Drone> findByState(String state);
	@Query("select u from Drone u where u.id = ?1")
	public Drone findByDroneId(long id);
	@Query("SELECT CASE WHEN count(e) > 0 THEN true ELSE false END FROM Drone e where e.serialNumber = ?1")
	public Boolean existsBySerialNumber(String serialNumber);
	@Query("select u from Drone u where u.serialNumber = ?1")
	public Drone findBySerialNumber(String serialNumber);
	@Query("select u from Drone u where u.model = ?1")
	public Drone findByModel(String model);
	public List<Drone> findByBatteryCapacityBetween(int levelA ,int levelB);
	
}
