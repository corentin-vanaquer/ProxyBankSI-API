package com.projet.proxy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SavingsAccount extends Account{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double rate;
	
	public SavingsAccount() {}

	public SavingsAccount(String accountNumber, double solde, double rate) {
		super(accountNumber, solde);
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
	
}