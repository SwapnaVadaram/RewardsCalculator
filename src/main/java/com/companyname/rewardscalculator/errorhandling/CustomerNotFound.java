package com.companyname.rewardscalculator.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.companyname.rewardscalculator.util.ConstantsUtil; 
/**
 * Exception class for throwing when customer is not found
 * @author Swapna Vadaram
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFound extends RuntimeException   
{  
	private static final long serialVersionUID = 1L;

	public CustomerNotFound(Long customerId)
  
	{  
		super(ConstantsUtil.NO_CUSTOMER_FOUND + customerId);
	}  
} 