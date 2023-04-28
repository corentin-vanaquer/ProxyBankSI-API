package com.projet.proxy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.proxy.model.CurrentAccount;
import com.projet.proxy.service.ICurrentAccountService;

@RestController
@RequestMapping("/currentAccounts")
public class CurrentAccountController {

	@Autowired ICurrentAccountService currentAccountService;
	
	/**
	 * Retrieves the list of currentAccounts.
	 * If the list is empty -> HTTP 404 (NOT_FOUND) response, with a body null
	 * else -> HTTP 200 (OK) response, with the list of currentAccounts in the response body.
	 * @return A ResponseEntity containing the list of currentAccounts and the corresponding HTTP response code.
	 */
	@GetMapping
	ResponseEntity<List<CurrentAccount>> getAccounts(){
		
		List<CurrentAccount> accountList = new ArrayList<>();
		accountList = currentAccountService.getAllCurrentAccounts();
		
	    switch (accountList.size()) {
        case 0:
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        default:
            return ResponseEntity.status(HttpStatus.OK).body(accountList);
    } 
	}
	
	/**
	 * Retrieves a currentAccounts by its ID.
	 * if currentAccounts is not found -> HTTP 404 (NOT_FOUND), with a body null.
	 * else -> HTTP 200 (OK) response, with the currentAccounts in the response body.
	 * @param id The ID of the currentAccounts to retrieve.
	 * @return A ResponseEntity containing the retrieved currentAccounts object, or an appropriate HTTP response if the currentAccounts does not exist.
	 */
	@GetMapping("/{id}")
	ResponseEntity<CurrentAccount> getAccountById(@PathVariable Long id){
		CurrentAccount currentAccountFetched = currentAccountService.getCurrentAccountById(id);
		
	    if (currentAccountFetched == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(currentAccountFetched);
	    }
	}
	
	/**
	 * Creates a new currentAccounts with the provided currentAccounts data.
	 * if creation fails ->  HTTP 400 (BAD_REQUEST) response, with a body null
	 * if creation is successful -> HTTP 201 (CREATED) with the created currentAccounts in the response body.
	 * @param account the account to be created
	 * @return ResponseEntity<Account> containing the created currentAccounts and the corresponding HTTP response code
	 */
	@PostMapping
	ResponseEntity<CurrentAccount> saveAccount(@RequestBody CurrentAccount account){
		CurrentAccount newAccount = currentAccountService.createCurrentAccount(account);
		if(newAccount == null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
	}
	
//	@PostMapping("/{senderAccountId}/transfer/{receivingAccountId}")
//	public ResponseEntity<CurrentAccount> doTransfer(@PathVariable long senderAccountId, @PathVariable long receivingAccountId, @RequestParam double amount){
//		currentAccountService.doVirement(virement);
//		return ResponseEntity.ok().build();
//	}
	
}
