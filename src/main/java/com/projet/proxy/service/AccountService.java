package com.projet.proxy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.proxy.model.Account;
import com.projet.proxy.repository.AccountDao;

@Service
public class AccountService implements IAccountService {

	@Autowired
	AccountDao accountDao;
	
	@Override
	public List<Account> getAllAccounts() {
		return accountDao.findAll();
	}

	@Override
	public Account getAccountById(Long id) {
		return accountDao.findById(id).orElse(null);
	}

	@Override
	public Account createAccount(Account account) {
		return accountDao.save(account);
	}

	@Override
	public Account updateAccount(Account account) {
		return accountDao.save(account);
	}

	@Override
	public boolean isAccountExists(Long id) {
		return accountDao.existsById(id);
	}

	@Override
	public void deleteAccountById(Long id) {
		accountDao.deleteById(id);
	}

}
