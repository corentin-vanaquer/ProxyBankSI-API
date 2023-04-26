package com.projet.proxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.proxy.model.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Long>  {

}
