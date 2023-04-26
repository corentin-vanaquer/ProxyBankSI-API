package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import com.projet.proxy.model.Advisor;

public interface AdvisorService {
	List<Advisor> getAllAdvisors();
	Advisor saveAdvisor(Advisor advisor);
	Optional<Advisor> getById(Long id);
	void deleteById(Long id);

}
