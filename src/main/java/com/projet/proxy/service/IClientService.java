package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import com.projet.proxy.model.Client;

public interface IClientService {
	
	List<Client> getAllClients();

	Client saveClient(Client client);

	Optional<Client> getById(Long id);

	void deleteById(Long id);

	boolean clientIdExist(Long id);

	Client updateClient(Client client);

	void addAdvisorToClient(Long clientId, Long advisorId);
	
	void addClientToCurrentAccount(Long ClientId, Long accountId);
	
	void addClientToSavingAccount(Long ClientId, Long accountId);
	
	List<Object> getAccountsFromClient(Long id);

}
