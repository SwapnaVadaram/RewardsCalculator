package com.companyname.rewardscalculator.service.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
/**
 * Model class for customer
 * @author Swapna Vadaram
 *
 */
@Entity
@Table(name = "customer")
@Setter
@Getter
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1768787987987987L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Purchase> purchases;

}
