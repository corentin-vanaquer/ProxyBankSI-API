package com.projet.proxy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
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
	
	
	
	
	

}
