package com.projet.proxy.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity 
public class Advisor  {
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String LastName;
	private String email;
	
//	@OneToMany(mappedBy=)
//	private Set<Client> clients = new HashSet<>();

	public Advisor() {}

	public Advisor(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.LastName = lastName;
		this.email = email;
	}

	
	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Advisor [id=" + id + ", firstName=" + firstName + ", LastName=" + LastName + ", email=" + email + "]";
	}
	
//	public Set<Client> getClients() {
//		return clients;
//	}
//
//	public void setClients(Set<Client> clients) {
//		this.clients = clients;
//	}
	
	
	
	
	
 
}
