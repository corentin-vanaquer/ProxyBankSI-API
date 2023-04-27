package com.projet.proxy.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String firstname;
	private String address;
	private String zipcode;
	private String city;
	private String phone;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "Advisor_Id")
	@JsonIgnore
	private Advisor advisor;
	
	@OneToMany(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JsonIgnore
	private Set<SavingsAccount> savingAccountsList = new HashSet<SavingsAccount>();
	
	@OneToMany(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JsonIgnore
	private Set<CurrentAccount> currentAccountList = new HashSet<CurrentAccount>();
	
	//Constructor 
	public Client(String name, String firstname, String address, String zipcode, String city, String phone) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.phone = phone;
	}

	public Client() {
		super();
	}
	
	//Class Method
	
	
	
	//Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	//To String
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", firstname=" + firstname + ", address=" + address
				+ ", zipcode=" + zipcode + ", city=" + city + ", phone=" + phone + "]";
	}

	public Advisor getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}
	
	
	public void setConseillerAttribuer(Advisor conseillerAttribuer) {
		this.advisor = conseillerAttribuer;
	}


	

	public Set<SavingsAccount> getSavingAccountsList() {
		return savingAccountsList;
	}

	public void setSavingAccountsList(Set<SavingsAccount> savingAccountsList) {
		this.savingAccountsList = savingAccountsList;
	}

	public Set<CurrentAccount> getCurrentAccountList() {
		return currentAccountList;
	}

	public void setCurrentAccountList(Set<CurrentAccount> currentAccountList) {
		this.currentAccountList = currentAccountList;
	}

	public void addSavingAccount(SavingsAccount savingsAccount) {
		savingAccountsList.add(savingsAccount);
		savingsAccount.setClient(this);
	}
	
	public void removeSavingAccount(SavingsAccount savingsAccount) {
		savingAccountsList.remove(savingsAccount);
		savingsAccount.setClient(null);
	}
	

	public void addCurrentAccount(CurrentAccount currentAccount) {
		currentAccountList.add(currentAccount);
		currentAccount.setClient(this);
	}
	
	public void removeAccount(SavingsAccount currentAccount) {
		currentAccountList.remove(currentAccount);
		currentAccount.setClient(null);
	}
}
