package com.companyname.rewardscalculator.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companyname.rewardscalculator.controller.model.PointsResponse;
import com.companyname.rewardscalculator.errorhandling.CustomerNotFound;
import com.companyname.rewardscalculator.errorhandling.PurchasesNotFound;
import com.companyname.rewardscalculator.repository.CustomerRepository;
import com.companyname.rewardscalculator.repository.PointsRepository;
import com.companyname.rewardscalculator.repository.PurchasesRepository;
import com.companyname.rewardscalculator.service.PointsCalculatorService;
import com.companyname.rewardscalculator.service.model.Customer;
import com.companyname.rewardscalculator.service.model.Points;
import com.companyname.rewardscalculator.service.model.Purchase;
import com.companyname.rewardscalculator.util.ConstantsUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * Implementation class for points calculator 
 * @author Swapna Vadaram
 *
 */
@Service
@Setter
@Getter
public class PointsCalculatorServiceImpl implements PointsCalculatorService {

	@Autowired
	 PointsRepository pointsRepository;

	@Autowired
	 PurchasesRepository purchasesRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(PointsCalculatorService.class);

	
	@Override
	/**
	 * Gets points for specific customer based on his purchases for past 3 months
	 */
	public PointsResponse getPoints(Long customerId) {
		LOGGER.info("Inside getPoints method");
		validateCustomerId(customerId);
		LocalDate date= LocalDate.now().minusMonths(ConstantsUtil.THREE_MONTHS);
		List<Purchase> purchases = purchasesRepository.findAllPurchasesByTime(date, customerId);
		LOGGER.debug("Size of purchases->"+purchases.size());
		List<Points> points = pointsRepository.findAll();
		LOGGER.debug("Size of points->"+points.size());
		List<Integer> result = purchases.stream().map(purchase -> getPoints(purchase, points))
				.collect(Collectors.toList());
		LOGGER.debug("Size of PurchasePoints->"+result.size());
		Integer pointsTotal = result.stream().reduce(Integer::sum)
				.orElseThrow(() -> new PurchasesNotFound(customerId));
		PointsResponse response = new PointsResponse();
		LOGGER.debug("Total Points ->"+pointsTotal);
		response.setId(customerId);
		response.setPoints(pointsTotal);
		return response;
	}
	
	/**
	 * Method to add all points from each purchase
	 * @param purchase
	 * @param points
	 * @return
	 */
	private Integer getPoints(Purchase purchase, List<Points> points) {
		return points.stream()
			.filter(point -> !(purchase.getAmount().compareTo(point.getMinValue()) == -1))
			.map(point -> {
			return getPurchasePoints(purchase, point);
		}).reduce(Integer::sum).orElse(0);
		
	}

	/**
	 * Method to calculate points based on configured data in the in-memory database
	 * @param purchase
	 * @param point
	 * @return
	 */
	private Integer getPurchasePoints(Purchase purchase, Points point) {
		LOGGER.info("Inside getPurchasePoints method");
		Integer pointsTotal = 0;
		if ((Objects.nonNull(point.getMaxValue()) && point.getMaxValue().compareTo(purchase.getAmount().setScale(0, RoundingMode.HALF_DOWN)) == -1)) {
			pointsTotal = (point.getMaxValue().setScale(0, RoundingMode.HALF_DOWN).subtract(point.getMinValue().setScale(0, RoundingMode.HALF_DOWN)))
					.multiply(BigDecimal.valueOf(point.getPoints())).intValue();
		}else {
			LOGGER.info("purchase.getAmount()->"+purchase.getAmount().setScale(0, RoundingMode.HALF_DOWN).intValue());
			LOGGER.info("point.getMinValue()->"+point.getMinValue());
			pointsTotal = (purchase.getAmount().setScale(0, RoundingMode.HALF_DOWN).subtract(point.getMinValue()))
					.multiply(BigDecimal.valueOf(point.getPoints())).intValue();
		}
		return pointsTotal;
	}
	
	/**
	 * Method that checks weather a customer with customerId exists or else throws CustomerNotFound exception
	 * @param customerId
	 */
	private void validateCustomerId(Long customerId) {
		Customer customer = customerRepository.getById(customerId);
		if(Objects.isNull(customer))
		{
			throw new CustomerNotFound(customerId);
		}
	}
}
