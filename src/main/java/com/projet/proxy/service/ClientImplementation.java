package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projet.proxy.model.Client;
import com.projet.proxy.repository.ClientDao;

@Service("client")
public class ClientImplementation implements ClientInterface {
	
	private ClientDao clientDao;

	public ClientImplementation(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client saveClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Client> getById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean clientIdExist(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client updateClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

}
