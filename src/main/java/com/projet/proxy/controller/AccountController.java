package com.projet.proxy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.proxy.model.Account;
import com.projet.proxy.service.IAccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired IAccountService accountService;
	
	/**
	 * Renvoie la liste des comptes :
	 * Si liste vide -> réponse HTTP 404 (NOT_FOUND).
	 * Si contient un élement -> réponse HTTP 200 (OK)
	 * 
	 * @return  ResponseEntity contenant la liste des comptes et le code de réponse HTTP correspondant.
	 */
	@GetMapping("/accounts")
	ResponseEntity<List<Account>> getAccounts(){
		
		List<Account> accountList = new ArrayList<>();
		accountList = accountService.getAllAccounts();
		
	    switch (accountList.size()) {
        case 0:
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        case 1:
            return ResponseEntity.status(HttpStatus.OK).body(accountList);
        default:
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    } 
	}
	
	
}
