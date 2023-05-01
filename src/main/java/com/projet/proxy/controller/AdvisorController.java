package com.projet.proxy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.projet.proxy.service.AdvisorService;
import com.projet.proxy.service.IClientService;

@RestController
@RequestMapping("/advisors")
public class AdvisorController {

	@Autowired
	private AdvisorService advisorService;

	@Autowired
	private IClientService clientService;

	/**
	 * Retrieves a list of all available advisors.
	 * 
	 * @return ResponseEntity with a list of Advisor objects and HTTP status code
	 *         200 (OK), or HTTP status code 204 (NO_CONTENT) if no advisors are
	 *         found.
	 */
	@GetMapping
	public ResponseEntity<List<Advisor>> listAdvisors() {
		List<Advisor> advisors = new ArrayList<>();
		advisors.addAll(advisorService.getAllAdvisors());
		if (advisors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(advisors, HttpStatus.OK);
	}

	/**
	 * Saves a new advisor to the system.
	 * 
	 * @param a The Advisor object to be saved in the system.
	 * @return A ResponseEntity object with the saved Advisor object and HTTP status
	 *         code 201 (CREATED) if the new advisor is saved successfully, or a
	 *         ResponseEntity object with HTTP status code 400 (BAD_REQUEST) if the
	 *         request is invalid or the new advisor is not saved.
	 */
	@PostMapping
	public ResponseEntity<Advisor> saveAdvisor(@RequestBody Advisor a) {
		Advisor newAdvisor = advisorService.saveAdvisor(a);
		System.out.println(newAdvisor);
		if (newAdvisor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(newAdvisor, HttpStatus.CREATED);

		}
	}

	/**
	 * Retrieves an advisor with the given ID from the system.
	 *
	 * @param id The ID of the advisor to retrieve.
	 * @return A ResponseEntity object with the requested Advisor object and HTTP
	 *         status code 200 (OK) if the advisor is found, or a ResponseEntity
	 *         object with HTTP status code 404 (NOT_FOUND) if the advisor is not
	 *         found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Advisor> getAdvisorById(@PathVariable Long id) {
		Optional<Advisor> advisor = advisorService.getById(id);
		if (advisor.isPresent()) {
			return new ResponseEntity<>(advisor.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Updates an existing advisor in the system.
	 * 
	 * @param a The Advisor object with updated information to be saved in the
	 *          system.
	 * @return A ResponseEntity object with the updated Advisor object and HTTP
	 *         status code 200 (OK) if the advisor is updated successfully, or a
	 *         ResponseEntity object with HTTP status code 404 (NOT_FOUND) if the
	 *         advisor with the given ID is not found.
	 */
	@PutMapping
	public ResponseEntity<Advisor> updateAdvisor(@RequestBody Advisor a) {
		if (advisorService.advisorIdExist(a.getId())) {
			Advisor advisorUpdated = advisorService.updateAdvisor(a);
			return ResponseEntity.status(HttpStatus.OK).body(advisorUpdated);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Deletes an advisor with the given ID from the system.
	 *
	 * @param id The ID of the advisor to be deleted.
	 */
	@DeleteMapping("/{id}")
	public void deleteAdvisor(@PathVariable long id) {
		if (advisorService.getById(id) == null) {
			new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		advisorService.deleteById(id);
	}

	/**
	 * Retrieves a list of accounts belonging to a client with the given ID.
	 *
	 * @param id The ID of the client whose accounts to retrieve.
	 * @return A ResponseEntity object with a list of Account objects belonging to
	 *         the client and HTTP status code 200 (OK) if the client and accounts
	 *         are found, or a ResponseEntity object with HTTP status code 404
	 *         (NOT_FOUND) if the client is not found or has no accounts.
	 */
	@GetMapping("/client/{id}/accounts")
	public ResponseEntity<Object> getAccountsFromClient(@PathVariable Long id) {
		if (clientService.clientIdExist(id)) {
			List<Object> accountsFromClient = clientService.getAccountsFromClient(id);
			if (accountsFromClient != null && !accountsFromClient.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(accountsFromClient);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
