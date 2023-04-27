package com.projet.proxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.proxy.model.SavingsAccount;

public interface SavingAccountDao extends JpaRepository<SavingsAccount, Long> {

}
