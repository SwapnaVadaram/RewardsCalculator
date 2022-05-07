package com.companyname.rewardscalculator.controller.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class for customer request
 * @author Swapna Vadaram
 *
 */
@Setter
@Getter
public class PointsResponse implements Serializable {

	private static final long serialVersionUID = 1768787987987987L;
	
	private Integer points;
	
	private Long id;


}
