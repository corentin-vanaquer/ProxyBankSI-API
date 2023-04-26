package com.projet.proxy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.proxy.model.Client;
import com.projet.proxy.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private ClientService client;

	public ClientController(ClientService client) {
		this.client = client;
	}
	
	//Use to get a List of all the clients
	@GetMapping
	public ResponseEntity<List<Client>> listClients(){
		List<Client> clients = new ArrayList<>();
		clients.addAll(client.getAllClients());
		if (clients.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}
	
	//Use to create a new Client
//	@PostMapping
//	public Client saveClient(@RequestBody Client c) {
//		client.saveClient(c);
//		if (client.) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity<>(c, HttpStatus.CREATED);
//	}

	//Use to get a single Client by his/her id
//	@GetMapping("/{id}")
//	public Optional<Client> getClientById(@PathVariable Long id) {
//		return client.getById(id);
//	}
//
//	@DeleteMapping("/{id}")
//	public void deleteClient(@PathVariable Long id) {
//		client.deleteById(id);
//	}
	
	
}
