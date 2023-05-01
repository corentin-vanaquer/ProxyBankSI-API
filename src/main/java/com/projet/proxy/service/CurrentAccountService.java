package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projet.proxy.model.Client;
import com.projet.proxy.model.CurrentAccount;
import com.projet.proxy.model.Virement;
import com.projet.proxy.repository.ClientDao;
import com.projet.proxy.repository.CurrentAccountDao;

@Service
public class CurrentAccountService implements ICurrentAccountService {

	@Autowired
	CurrentAccountDao currentAccountDao;

	@Autowired
	ClientDao clientDao;
	
	public CurrentAccountService(CurrentAccountDao currentAccountDao) {
        this.currentAccountDao = currentAccountDao;
    }

	public CurrentAccountService() {
	}

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
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Account already exists with id: " + account.getId());
		}
		return currentAccountDao.save(account);
	}

	@Override
	public CurrentAccount updateCurrentAccount(CurrentAccount updatedAccount) {
		if (!currentAccountDao.existsById(updatedAccount.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Account not found with id: " + updatedAccount.getId());
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

	/**
	 * This method adds a client to a current account. It takes two parameters:
	 * `clientId` and `accountId`, which are used to fetch the corresponding
	 * `Client` and `CurrentAccount` objects from the database using their
	 * respective IDs. If both objects are present, the `Client` is added to the
	 * `CurrentAccount` and the changes are persisted to the database. If either the
	 * `Client` or `CurrentAccount` is not found, the method does nothing.
	 */
	@Override
	public void addClientToCurrentAccount(Long clientId, Long accountId) {
		Optional<CurrentAccount> account = currentAccountDao.findById(accountId);
		Optional<Client> client = clientDao.findById(clientId);
		if (account.isPresent() && client.isPresent()) {
			CurrentAccount accountOne = account.get();
			Client clientOne = client.get();
			accountOne.setClient(clientOne);
			currentAccountDao.save(accountOne);
		}
	}

	/**
	 * This method performs a transfer operation between two current accounts. It
	 * first retrieves the sender and receiver accounts from the database using
	 * their respective IDs provided in the Virement object. If either of the
	 * accounts is not found in the database, an exception is thrown.
	 * 
	 * Once both accounts are retrieved, the method calls the doTransfert() method
	 * passing in the sender account, receiving account, and the amount to transfer.
	 * The doTransfert() method is expected to perform the necessary checks to
	 * ensure that the transfer is valid and then perform the transfer.
	 */
	public void doVirement(Virement v) {
		Optional<CurrentAccount> optionalSenderAccount = currentAccountDao.findById(v.getFirstId());
		CurrentAccount senderAccount = optionalSenderAccount.orElseThrow();
		Optional<CurrentAccount> optionalReceivingAccount = currentAccountDao.findById(v.getSecondId());
		CurrentAccount receivingAccount = optionalReceivingAccount.orElseThrow();

		doTransfert(senderAccount, receivingAccount, v.getAmount());

	}

	/**
	 * This method performs a transfer of funds from one account to another. It
	 * takes in two parameters of type CurrentAccount, representing the sender and
	 * receiving accounts, and a double value representing the amount to transfer.
	 * 
	 * It first withdraws the amount from the sender account using the withdrawal
	 * method of the CurrentAccount class and then deposits the same amount into the
	 * receiving account using the deposit method of the same class. Finally, it
	 * saves both accounts using the corresponding DAO's save method.
	 * 
	 * This method could potentially throw exceptions if the withdrawal fails due to
	 * insufficient funds in the sender account or if one of the accounts does not
	 * exist in the database.
	 */
	@Override
	public void doTransfert(CurrentAccount senderAccount, CurrentAccount receivingAccount, double d) {
		senderAccount.withdrawal(d);
		receivingAccount.deposit(d);

		currentAccountDao.save(senderAccount);
		currentAccountDao.save(receivingAccount);

	}
}
