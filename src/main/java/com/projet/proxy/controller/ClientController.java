package com.projet.proxy.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.proxy.model.Advisor;
import com.projet.proxy.model.Client;
import com.projet.proxy.service.IClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private IClientService client;

	public ClientController(IClientService client) {
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
	@PostMapping
	public ResponseEntity<Client> saveClient(@RequestBody Client c) {
		Client newClient = client.saveClient(c);
		if (newClient == null ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(newClient, HttpStatus.CREATED);
		}
	}

	//Use to get a single Client by his/her id
	@GetMapping("/{id}")
	ResponseEntity<Optional<Client>> getClientById(@PathVariable Long id){
		Optional<Client> clientFetched = client.getById(id);
		
	    if (clientFetched == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(clientFetched);
	    }
	}

	//Use To Delete a Client 
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable long id){
		if(client.getById(id)==null) {
			new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
		client.deleteById(id);
	}
	
	@PutMapping
	public ResponseEntity<Client> updateClient(@RequestBody Client c, @RequestBody Advisor a) {
		if (client.clientIdExist(c.getId())) {
			Client updateClient = client.updateClient(c);
			updateClient.setAdvisor(a);
			return ResponseEntity.ok(updateClient);
		}
		Client saveClient = client.updateClient(c);
		return ResponseEntity.created(URI.create("clients/" + saveClient.getId())).body(saveClient);
	}
	
	
}
