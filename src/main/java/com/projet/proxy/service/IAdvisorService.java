package com.projet.proxy.service;

import java.util.List;
import java.util.Optional;

import com.projet.proxy.model.Advisor;

public interface IAdvisorService {
	List<Advisor> getAllAdvisors();
	Advisor saveAdvisor(Advisor advisor);
	Optional<Advisor> getById(Long id);
	Advisor updateAdvisor(Advisor advisor);
	void deleteById(Long id);
	boolean advisorIdExist(Long id);

}
