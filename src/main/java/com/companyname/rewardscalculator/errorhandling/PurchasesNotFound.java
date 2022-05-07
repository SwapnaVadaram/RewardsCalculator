package com.companyname.rewardscalculator.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.companyname.rewardscalculator.util.ConstantsUtil; 
/**
 * Exception class for handling purchases not found
 * @author Swapna Vadaram
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PurchasesNotFound extends RuntimeException   
{  
	private static final long serialVersionUID = 1L;

	public PurchasesNotFound(Long customerId)
  
	{  
		super(ConstantsUtil.NO_PURCHASES_FOUND + customerId);
	}  
} 


