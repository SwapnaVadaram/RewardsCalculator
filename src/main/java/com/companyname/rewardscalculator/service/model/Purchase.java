package com.companyname.rewardscalculator.service.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
/**
 * Model class for purchases
 * @author Swapna Vadaram
 *
 */
@Setter
@Getter
@Entity
@Table(name="purchase")
public class Purchase implements Serializable{
	
	private static final long serialVersionUID = 123487298L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="amount")
	private BigDecimal amount;
	@Column(name="time")
	private LocalDate time;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
}
