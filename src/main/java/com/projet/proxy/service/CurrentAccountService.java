package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projet.proxy.model.Client;
import com.projet.proxy.model.CurrentAccount;
import com.projet.proxy.repository.ClientDao;
import com.projet.proxy.repository.CurrentAccountDao;

@Service
public class CurrentAccountService implements ICurrentAccountService {

	@Autowired
	CurrentAccountDao currentAccountDao;
	
	@Autowired
	ClientDao clientDao;
	
	@Override
	public List<CurrentAccount> getAllCurrentAccounts() {
		return currentAccountDao.findAll();
	}

	@Override
	public CurrentAccount getCurrentAccountById(Long id) {
	    Optional<CurrentAccount> account = currentAccountDao.findById(id);
	    if (account.isPresent()) {
	        return account.get();
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + id);
	    }
	}


	@Override
	public CurrentAccount createCurrentAccount(CurrentAccount account) {
	    if (account.getId() != null && currentAccountDao.existsById(account.getId())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account already exists with id: " + account.getId());
	    }
	    return currentAccountDao.save(account);
	}


	@Override
	public CurrentAccount updateCurrentAccount(CurrentAccount updatedAccount) {
	    if (!currentAccountDao.existsById(updatedAccount.getId())) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + updatedAccount.getId());
	    }
	    return currentAccountDao.save(updatedAccount);
	}


	@Override
	public boolean isCurrentAccountExists(Long id) {
		return currentAccountDao.existsById(id);
	}

	@Override
	public void deleteCurrentAccountById(Long id) {
	    if (currentAccountDao.existsById(id)) {
	    	currentAccountDao.deleteById(id);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + id);
	    }
	}

	@Override
	public void addClientToCurrentAccount(Long clientId, Long accountId) {
		Optional<CurrentAccount> account = currentAccountDao.findById(accountId);
		Optional<Client> client = clientDao.findById(clientId);
		if(account.isPresent() && client.isPresent()) {
			CurrentAccount accountOne = account.get();
			Client clientOne = client.get();
			accountOne.setClient(clientOne);
			currentAccountDao.save(accountOne);
		}
	}

}
