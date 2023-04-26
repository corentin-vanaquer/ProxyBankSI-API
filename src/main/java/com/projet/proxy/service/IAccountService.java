package com.projet.proxy.service;

import java.util.List;

import com.projet.proxy.model.Account;

public interface IAccountService {
	
	List<Account> getAllComptes();
	Account getAccountById(Long id);
	Account createAccount(Account account);
	Account updateAccount(Account account);
	boolean isAccountExists(Long id);
	void deleteAccountById(Long id);

}
