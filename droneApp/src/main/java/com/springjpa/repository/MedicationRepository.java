package com.springjpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springjpa.model.Medication;

public interface MedicationRepository extends CrudRepository<Medication, Long>{
	@Query("select u from Medication u where u.code = ?1")
	public Medication findByCode(String code);
	
}
