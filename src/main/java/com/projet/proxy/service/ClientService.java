package com.projet.proxy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.proxy.model.Advisor;
import com.projet.proxy.model.Client;
import com.projet.proxy.model.CurrentAccount;
import com.projet.proxy.model.SavingsAccount;
import com.projet.proxy.repository.AdvisorDao;
import com.projet.proxy.repository.ClientDao;
import com.projet.proxy.repository.CurrentAccountDao;
import com.projet.proxy.repository.SavingAccountDao;

@Service("client")
public class ClientService implements IClientService{

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private AdvisorDao advisorDao;
	
	@Autowired
	private CurrentAccountDao currentAccountDao;
	
	@Autowired
	private SavingAccountDao savingAccountDao;

	public ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public List<Client> getAllClients() {
		return clientDao.findAll();
	}

	@Override
	public Client saveClient(Client client) {
		return clientDao.save(client);
	}

	@Override
	public Optional<Client> getById(Long id) {
		return clientDao.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		clientDao.deleteById(id);		
	}

	@Override
	public boolean clientIdExist(Long id) {
		return clientDao.existsById(id);
	}

	@Override
	public Client updateClient(Client client) {
		return clientDao.save(client);
	}

	@Override
	public void addAdvisorToClient(Long clientId, Long advisorId) {
        Optional<Client> c = clientDao.findById(clientId);
        Optional<Advisor> a = advisorDao.findById(advisorId);
        if (c.isPresent() && a.isPresent()) {
            Client c1 = c.get();
            Advisor a1 = a.get();
            c1.setAdvisor(a1);
            clientDao.save(c1);
        }
		
	}

	@Override
	public List<Object> getAccountsFromClient(Long id) {
	    Optional<Client> result = clientDao.findById(id);
	    if (result.isPresent()) {
	        Client clientFetched = result.get();
	        List<Object> accountList = new ArrayList<>();
	        accountList.addAll(clientFetched.getCurrentAccountList());
	        accountList.addAll(clientFetched.getSavingAccountsList());
	        return accountList;
	    } else {
	        throw new IllegalArgumentException("Client non trouvé pour l'id: " + id);
	    }
	}

	@Override
	public void addClientToCurrentAccount(Long clientId, Long accountId) {
		Optional<CurrentAccount> account = currentAccountDao.findById(accountId);
		Optional<Client> client = clientDao.findById(clientId);
		if (account.isPresent() && client.isPresent()) {
			CurrentAccount accountOne = account.get();
			Client clientOne = client.get();
			clientOne.addCurrentAccount(accountOne);
			clientDao.save(clientOne);
		}
		
	}

	@Override
	public void addClientToSavingAccount(Long clientId, Long accountId) {
		Optional<SavingsAccount> account = savingAccountDao.findById(accountId);
		Optional<Client> client = clientDao.findById(clientId);
		if (account.isPresent() && client.isPresent()) {
			SavingsAccount accountOne = account.get();
			Client clientOne = client.get();
			clientOne.addSavingAccount(accountOne);
			clientDao.save(clientOne);
		}
		
	}


}
