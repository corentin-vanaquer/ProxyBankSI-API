package com.projet.proxy.service;

import java.util.List;

import com.projet.proxy.model.CurrentAccount;

public interface ICurrentAccountService {
	
	List<CurrentAccount> getAllCurrentAccounts();
	CurrentAccount getCurrentAccountById(Long id);
	CurrentAccount createCurrentAccount(CurrentAccount account);
	CurrentAccount updateCurrentAccount(CurrentAccount account);
	boolean isCurrentAccountExists(Long id);
	void deleteCurrentAccountById(Long id);
	void addClientToCurrentAccount(Long ClientId, Long accountId);

}
