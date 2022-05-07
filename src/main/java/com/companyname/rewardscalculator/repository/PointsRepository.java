package com.companyname.rewardscalculator.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.companyname.rewardscalculator.service.model.Points;
/**
 * Repository class for querying points table
 * @author Swapna Vadaram
 *
 */
public interface PointsRepository extends JpaRepository<Points, Long> {


}

