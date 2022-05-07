package com.companyname.rewardscalculator.service.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
/**
 * Model class for points
 * @author Swapna Vadaram
 *
 */
@Setter
@Getter
@Entity
@Table(name = "points")
public class Points implements Serializable {

	private static final long serialVersionUID = 1768787987987987L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name= "min_value")
	private BigDecimal minValue;
	@Column(name= "max_value")
	private BigDecimal maxValue;
	@Column(name="points")
	private Integer points;

}
