package com.projet.proxy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.proxy.model.Advisor;

@Repository
public interface AdvisorDao extends JpaRepository<Advisor, Long> {

}
