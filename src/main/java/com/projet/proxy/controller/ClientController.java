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

import com.projet.proxy.model.Client;
import com.projet.proxy.service.IClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private IClientService client;

	public ClientController(IClientService client) {
		this.client = client;
	}

	/**
	 * Retrieves a list of all clients from the system.
	 *
	 * @return A ResponseEntity object with a list of Client objects and HTTP status
	 *         code 200 (OK) if clients are found, or a ResponseEntity object with
	 *         HTTP status code 204 (NO_CONTENT) if no clients are found.
	 */
	@GetMapping
	public ResponseEntity<List<Client>> listClients() {
		List<Client> clients = new ArrayList<>();
		clients.addAll(client.getAllClients());
		if (clients.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}

	/**
	 * Saves a new client to the system.
	 *
	 * @param c The Client object to be saved.
	 * @return A ResponseEntity object with the saved Client object and HTTP status
	 *         code 201 (CREATED) if the client is saved successfully, or a
	 *         ResponseEntity object with HTTP status code 400 (BAD_REQUEST) if the
	 *         client is null or fails to save.
	 */
	@PostMapping
	public ResponseEntity<Client> saveClient(@RequestBody Client c) {
		Client newClient = client.saveClient(c);
		if (newClient == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(newClient, HttpStatus.CREATED);
		}
	}

	/**
	 * Retrieves a client with the given ID.
	 *
	 * @param id The ID of the client to retrieve.
	 * @return A ResponseEntity object with the Client object and HTTP status code
	 *         200 (OK) if the client is found, or a ResponseEntity object with HTTP
	 *         status code 404 (NOT_FOUND) if the client is not found.
	 */
	@GetMapping("/{id}")
	ResponseEntity<Optional<Client>> getClientById(@PathVariable Long id) {
		Optional<Client> clientFetched = client.getById(id);

		if (clientFetched == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(clientFetched);
		}
	}

	/**
	 * Deletes the client with the given ID.
	 *
	 * @param id The ID of the client to delete.
	 */
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable long id) {
		if (client.getById(id) == null) {
			new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		client.deleteById(id);
	}

	/**
	 * Updates the details of an existing client with the given client object. If
	 * the client with the given ID exists in the database, its details are updated
	 * and a ResponseEntity object with the updated client object and HTTP status OK
	 * is returned. If the client with the given ID does not exist, a new client is
	 * saved with the given details and a ResponseEntity object with the saved
	 * client object and HTTP status CREATED is returned.
	 *
	 * @param c The client object with updated details.
	 * @return ResponseEntity object with the updated client object and HTTP status
	 *         OK if the client with the given ID exists, or a ResponseEntity object
	 *         with the saved client object and HTTP status CREATED if the client
	 *         with the given ID does not exist.
	 */
	@PutMapping
	public ResponseEntity<Client> updateClient(@RequestBody Client c) {
		if (client.clientIdExist(c.getId())) {
			Client updateClient = client.updateClient(c);
			return ResponseEntity.ok(updateClient);
		}
		Client saveClient = client.updateClient(c);
		return ResponseEntity.created(URI.create("clients/" + saveClient.getId())).body(saveClient);
	}

}
