package com.projet.proxy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.proxy.model.SavingsAccount;

@Repository
public interface SavingAccountDao extends JpaRepository<SavingsAccount, Long> {


}
