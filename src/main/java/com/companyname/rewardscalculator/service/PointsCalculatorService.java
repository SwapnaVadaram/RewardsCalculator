package com.companyname.rewardscalculator.service;

import com.companyname.rewardscalculator.controller.model.PointsResponse;

/**
 * Interface for points calculator service
 * @author Swapna Vadaram
 *
 */
public interface PointsCalculatorService {
	
	PointsResponse getPoints(Long customerId);

}
