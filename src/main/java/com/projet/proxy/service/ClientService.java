package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.proxy.model.Advisor;
import com.projet.proxy.model.Client;
import com.projet.proxy.repository.AdvisorDao;
import com.projet.proxy.repository.ClientDao;

@Service("client")
public class ClientService implements IClientService{

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private AdvisorDao advisorDao;

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

}
