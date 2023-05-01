package com.projet.proxy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.proxy.model.SavingsAccount;
import com.projet.proxy.service.ISavingAccountService;

@RestController
@RequestMapping("/savingAccounts")
public class SavingAccountController {

	@Autowired
	ISavingAccountService savingAccountService;

	@GetMapping
	ResponseEntity<List<SavingsAccount>> getAccounts() {

		List<SavingsAccount> accountList = new ArrayList<>();
		accountList = savingAccountService.getAllSavingAccounts();

		switch (accountList.size()) {
		case 0:
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		default:
			return ResponseEntity.status(HttpStatus.OK).body(accountList);
		}
	}

	@GetMapping("/{id}")
	ResponseEntity<SavingsAccount> getAccountById(@PathVariable Long id) {
		SavingsAccount savingAccountFetched = savingAccountService.getSavingAccountById(id);

		if (savingAccountFetched == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(savingAccountFetched);
		}
	}

	@PostMapping
	ResponseEntity<SavingsAccount> saveAccount(@RequestBody SavingsAccount account) {
		SavingsAccount newAccount = savingAccountService.createSavingAccount(account);
		if (newAccount == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
	}

//	@PutMapping("transfer/{senderAccountId}/{receivingAccountId}")
//	public ResponseEntity<SavingsAccount> doTransfer(@PathVariable long senderAccountId, @PathVariable long receivingAccountId, @RequestBody double amount){
//		//System.out.println(senderAccountId + receivingAccountId + amount);
//		savingAccountService.virement(senderAccountId, receivingAccountId, amount);
//		
//		return ResponseEntity.ok().build();
//	}

}
