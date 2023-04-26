package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projet.proxy.model.Advisor;
import com.projet.proxy.repository.AdvisorDao;

@Service("advisor")
public class IAdvisorService implements AdvisorService {
	private AdvisorDao advisorDao;

	@Override
	public List<Advisor> getAllAdvisors() {
		return advisorDao.findAll();
	}

	@Override
	public Advisor saveAdvisor(Advisor advisor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Advisor> getById(Long id) {
		return advisorDao.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		advisorDao.deleteById(id);	
	}

}
