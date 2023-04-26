package com.projet.proxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.proxy.model.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long>{

}
