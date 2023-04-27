package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.projet.proxy.model.Client;
import com.projet.proxy.model.SavingsAccount;
import com.projet.proxy.repository.ClientDao;
import com.projet.proxy.repository.SavingAccountDao;

public class SavingAccountService implements ISavingAccountService {
	
	@Autowired
	SavingAccountDao savingAccountDao;
	ClientDao clientDao;

	@Override
	public List<SavingsAccount> getAllSavingAccounts() {
		
		return savingAccountDao.findAll();
	}

	@Override
	public SavingsAccount getSavingAccountById(Long id) {
		Optional<SavingsAccount> savingAccount = savingAccountDao.findById(id);
		if(savingAccount.isPresent()) {
			return savingAccount.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + id);
		}
		
	}

	@Override
	public SavingsAccount createSavingAccount(SavingsAccount savingsAccount) {
		 if (savingsAccount.getId() != null && savingAccountDao.existsById(savingsAccount.getId())) {
		        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account already exists with id: " + savingsAccount.getId());
		    }
		    return savingAccountDao.save(savingsAccount);
	}

	@Override
	public SavingsAccount updateSavingAccount(SavingsAccount updateSavingsAccount) {
		 if (!savingAccountDao.existsById(updateSavingsAccount.getId())) {
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + updateSavingsAccount.getId());
		    }
		    return savingAccountDao.save(updateSavingsAccount);
	}

	@Override
	public boolean isSavingAccountExists(Long id) {
		return savingAccountDao.existsById(id);
	}

	@Override
	public void deleteSavingAccountById(Long id) {
		if (savingAccountDao.existsById(id)) {
	        savingAccountDao.deleteById(id);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + id);
	    }
	}

	@Override
	public void addClientToSavingAccount(Long ClientId, Long accountId) {
		Optional<SavingsAccount> savingsAccount = savingAccountDao.findById(accountId);
		Optional<Client> client = clientDao.findById(ClientId);
		if(savingsAccount.isPresent() && client.isPresent()) {
			SavingsAccount savingAccountOne = savingsAccount.get();
			Client clientOne = client.get();
			savingAccountOne.setClient(clientOne);
			savingAccountDao.save(savingAccountOne);
	}

	}
}
