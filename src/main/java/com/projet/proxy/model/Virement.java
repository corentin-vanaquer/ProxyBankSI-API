package com.projet.proxy.model;

public class Virement {

	private long firstId;
	private long secondId;

	private double amount;

	public long getFirstId() {
		return firstId;
	}

	public void setFirstId(long firstId) {
		this.firstId = firstId;
	}

	public long getSecondId() {
		return secondId;
	}

	public void setSecondId(long secondId) {
		this.secondId = secondId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Virement(long firstId, long secondId, double amount) {
		this.firstId = firstId;
		this.secondId = secondId;
		this.amount = amount;
	}

}