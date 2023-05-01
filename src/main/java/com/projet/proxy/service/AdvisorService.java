package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.proxy.model.Advisor;
import com.projet.proxy.repository.AdvisorDao;

@Service("advisor")
public class AdvisorService implements IAdvisorService {

	@Autowired
	private AdvisorDao advisorDao;

	@Override
	public List<Advisor> getAllAdvisors() {
		return advisorDao.findAll();
	}

	@Override
	public Advisor saveAdvisor(Advisor advisor) {
		return advisorDao.save(advisor);
	}

	@Override
	public Optional<Advisor> getById(Long id) {
		return advisorDao.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		advisorDao.deleteById(id);
	}

	@Override
	public Advisor updateAdvisor(Advisor advisor) {
		return advisorDao.save(advisor);
	}

	@Override
	public boolean advisorIdExist(Long id) {
		return advisorDao.existsById(id);
	}

}
