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
public class CurrentAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String accountNumber;
	private LocalDateTime dateCreation;
	private double solde;
	private Integer threshold;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "Client_Id")
	private Client client;

//	@OneToMany(mappedBy = "firstAccount", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//	@JsonIgnore
//	private Set<Virement> virementList = new HashSet<Virement>();

	public CurrentAccount() {
	}

	public CurrentAccount(String accountNumber, double solde, Integer threshold) {
		this.accountNumber = accountNumber;
		this.solde = solde;
		this.threshold = threshold;
		this.dateCreation = LocalDateTime.now();
	}

	/**
	 * 
	 * Withdraws a given amount from the account balance if the balance is
	 * sufficient.
	 * 
	 * @param amount the amount to withdraw
	 * @throws IllegalArgumentException if the account balance is insufficient to
	 *                                  perform the operation
	 */
	public void withdrawal(double amount) {
		if (amount <= solde) {
			solde -= amount;
		} else {
			throw new IllegalArgumentException("Not Enough Fond");
		}
	}

	public void deposit(double amount) {
		solde += amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
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

}
