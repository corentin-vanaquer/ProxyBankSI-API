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
import org.springframework.web.bind.annotation.RestController;

import com.projet.proxy.model.Account;
import com.projet.proxy.service.IAccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired IAccountService accountService;
	
	/**
	 * Retrieves the list of accounts.
	 * If the list is empty -> HTTP 404 (NOT_FOUND) response, with a body null
	 * else -> HTTP 200 (OK) response, with the list of accounts in the response body.
	 * @return A ResponseEntity containing the list of accounts and the corresponding HTTP response code.
	 */
	@GetMapping("/accounts")
	ResponseEntity<List<Account>> getAccounts(){
		
		List<Account> accountList = new ArrayList<>();
		accountList = accountService.getAllAccounts();
		
	    switch (accountList.size()) {
        case 0:
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        default:
            return ResponseEntity.status(HttpStatus.OK).body(accountList);
    } 
	}
	
	/**
	 * Retrieves a bank account by its ID.
	 * if account is not found -> HTTP 404 (NOT_FOUND), with a body null.
	 * else -> HTTP 200 (OK) response, with the account in the response body.
	 * @param id The ID of the bank account to retrieve.
	 * @return A ResponseEntity containing the retrieved Account object, or an appropriate HTTP response if the account does not exist.
	 */
	@GetMapping("/accounts/{id}")
	ResponseEntity<Account> getAccountById(@PathVariable Long id){
		Account accountFetched = accountService.getAccountById(id);
		
	    if (accountFetched == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(accountFetched);
	    }
	}
	
	/**
	 * Creates a new account with the provided account data.
	 * if creation fails ->  HTTP 400 (BAD_REQUEST) response, with a body null
	 * if creation is successful -> HTTP 201 (CREATED) with the created account in the response body.
	 * @param account the account to be created
	 * @return ResponseEntity<Account> containing the created account and the corresponding HTTP response code
	 */
	@PostMapping("/accounts")
	ResponseEntity<Account> saveAccount(@RequestBody Account account){
		Account newAccount = accountService.createAccount(account);
		if(newAccount == null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
	}
	
}
