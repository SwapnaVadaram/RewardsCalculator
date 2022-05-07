package com.companyname.rewardscalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.companyname.rewardscalculator.service.model.Customer;
/**
 * Repository class for querying customer table
 * @Repository
 * @author Swapna Vadaram
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	@Query("select c from Customer c where c.id=:id")
	Customer getById(@Param("id") Long id);	
	
}
