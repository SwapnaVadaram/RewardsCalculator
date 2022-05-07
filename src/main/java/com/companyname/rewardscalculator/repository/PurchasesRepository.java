package com.companyname.rewardscalculator.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.companyname.rewardscalculator.service.model.Purchase;

/**
 * Repository class for querying purchases table
 * @author Swapna Vadaram
 *
 */
public interface PurchasesRepository extends JpaRepository<Purchase, Long> {

	@Query("select p from Purchase p where p.time>=:threeMonths and p.customer.id=:id")
	List<Purchase> findAllPurchasesByTime(@Param("threeMonths") LocalDate threeMonths, @Param("id") Long id);	

}
