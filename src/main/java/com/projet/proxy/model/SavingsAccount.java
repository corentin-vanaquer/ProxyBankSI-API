package com.projet.proxy.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SavingsAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String accountNumber;
	private LocalDateTime dateCreation;
	private double solde;
	private double rate;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "Client_Id")
	private Client client;

	public SavingsAccount() {
	}

	public SavingsAccount(String accountNumber, double solde, double rate) {
		this.accountNumber = accountNumber;
		this.dateCreation = LocalDateTime.now();
		this.solde = solde;
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}



	@Override
	public String toString() {
		return "SavingsAccount [id=" + id + ", accountNumber=" + accountNumber + ", dateCreation=" + dateCreation
				+ ", solde=" + solde + ", rate=" + rate + ", client=" + client + "]";
	}

}