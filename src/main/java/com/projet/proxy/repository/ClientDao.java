package com.projet.proxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.proxy.model.Client;

public interface ClientDao extends JpaRepository<Client, Long>  {

}
