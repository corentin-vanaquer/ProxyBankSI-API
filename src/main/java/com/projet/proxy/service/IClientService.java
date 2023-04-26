package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projet.proxy.model.Client;
import com.projet.proxy.repository.ClientDao;

@Service("client")
public class IClientService implements ClientService{

	private ClientDao clientDao;

	public IClientService(ClientDao clientDao) {
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

}
