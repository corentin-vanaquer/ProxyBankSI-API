package com.projet.proxy.service;

import java.util.List;


import com.projet.proxy.model.SavingsAccount;

public interface ISavingAccountService {

	List<SavingsAccount> getAllSavingAccounts();
	SavingsAccount getSavingAccountById(Long id);
	SavingsAccount createSavingAccount(SavingsAccount savingsAccount);
	SavingsAccount updateSavingAccount(SavingsAccount savingsAccount);
	boolean isSavingAccountExists(Long id);
	void deleteSavingAccountById(Long id);
	void addClientToSavingAccount(Long ClientId, Long accountId);
}
