package com.projet.proxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.proxy.model.CurrentAccount;


@Repository
public interface CurrentAccountDao extends JpaRepository<CurrentAccount, Long>{

}
