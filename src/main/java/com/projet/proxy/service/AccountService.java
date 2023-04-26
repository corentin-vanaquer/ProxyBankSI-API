package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projet.proxy.model.Account;
import com.projet.proxy.model.Client;
import com.projet.proxy.repository.AccountDao;
import com.projet.proxy.repository.ClientDao;

@Service
public class AccountService implements IAccountService {

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	ClientDao clientDao;
	
	@Override
	public List<Account> getAllAccounts() {
		return accountDao.findAll();
	}

	@Override
	public Account getAccountById(Long id) {
	    Optional<Account> account = accountDao.findById(id);
	    if (account.isPresent()) {
	        return account.get();
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + id);
	    }
	}


	@Override
	public Account createAccount(Account account) {
	    if (account.getId() != null && accountDao.existsById(account.getId())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account already exists with id: " + account.getId());
	    }
	    return accountDao.save(account);
	}


	@Override
	public Account updateAccount(Account updatedAccount) {
	    if (!accountDao.existsById(updatedAccount.getId())) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + updatedAccount.getId());
	    }
	    return accountDao.save(updatedAccount);
	}


	@Override
	public boolean isAccountExists(Long id) {
		return accountDao.existsById(id);
	}

	@Override
	public void deleteAccountById(Long id) {
	    if (accountDao.existsById(id)) {
	        accountDao.deleteById(id);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + id);
	    }
	}

	@Override
	public void addClientToAccount(Long clientId, Long accountId) {
		Optional<Account> account = accountDao.findById(accountId);
		Optional<Client> client = clientDao.findById(clientId);
		if(account.isPresent() && client.isPresent()) {
			Account accountOne = account.get();
			Client clientOne = client.get();
			accountOne.setClient(clientOne);
			accountDao.save(accountOne);
		}
	}

}
