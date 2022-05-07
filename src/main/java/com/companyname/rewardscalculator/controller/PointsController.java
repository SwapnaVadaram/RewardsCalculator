package com.companyname.rewardscalculator.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.rewardscalculator.controller.model.PointsResponse;
import com.companyname.rewardscalculator.service.PointsCalculatorService;

/*
 * @swapna vadaram
 * This is controller class for calculating reward points
 */
@RestController
@RequestMapping("/pointscalculator")
public class PointsController {

	@Autowired
	PointsCalculatorService pointsCalculatorService;


	private static final Logger LOGGER = LoggerFactory.getLogger(PointsCalculatorService.class);

	/**
	 * Get method for getting points for purchases made by customer
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/customer/{id}", produces = "application/json")
	public ResponseEntity<PointsResponse> getPoints(@PathVariable Long id) {
		LOGGER.info("Inside getPoints in PointsController for customer Id " + id);
		
		return new ResponseEntity<PointsResponse>(pointsCalculatorService.getPoints(id), null, HttpStatus.OK);

	}
}
