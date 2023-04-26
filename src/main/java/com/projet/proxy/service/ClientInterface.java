package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import com.projet.proxy.model.Client;

public interface ClientInterface {

	List<Client> getAllClients();

	Client saveClient(Client client);

	Optional<Client> getById(Long id);

	void deleteById(Long id);

	boolean clientIdExist(Long id);

	Client updateClient(Client client);

	//void AddAdvisorToClient(Long clientId, Long advisorId);
	
}
