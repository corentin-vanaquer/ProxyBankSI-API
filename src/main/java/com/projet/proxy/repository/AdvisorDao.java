package com.projet.proxy.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.proxy.model.Advisor;

public interface AdvisorDao extends JpaRepository<Advisor, Long> {

}
