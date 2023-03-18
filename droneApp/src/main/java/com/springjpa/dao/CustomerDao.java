package com.springjpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springjpa.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, Long>{
	
	public List<Customer> findByLastName(String lastName);
	@Query("select u from Customer u where u.id = ?1")
	public Customer findByCustomerId(long id);
	public List<Customer> findAllByOrderByIdAsc();
	@Query("SELECT CASE WHEN count(e) > 0 THEN true ELSE false END FROM Customer e where e.firstName = ?1")
	public Boolean existsByName(String firstName);
	public Customer findByFirstName(String firstName);
}
