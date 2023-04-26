package com.projet.proxy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projet.proxy.model.Account;
import com.projet.proxy.model.Advisor;
import com.projet.proxy.model.Client;
import com.projet.proxy.model.CurrentAccount;
import com.projet.proxy.model.SavingsAccount;

import jakarta.annotation.PostConstruct;

@Component
public class InitDb {
	
	@Autowired
	private AdvisorDao advisorDao;
	
	@PostConstruct
	public void DbStart() {
		
		// ~~~~~ INSTANCE ~~~~~
		Client clientOne = new Client("Bob", "Hob", "10 rue alexandre", "35000", "Rennes", "0612211221");
		Client clientTwo = new Client("Lucie", "Martin", "20 rue alexandre", "35000", "Paris", "06666666");
		Client clientThree = new Client("Daniel", "Coop", "30 rue alexandre", "35000", "Toulouse", "0611076533");

		Account accountOne = new CurrentAccount("1111", 1000.0, 1000);
		Account accountTwo= new CurrentAccount("2222", 2000.0, 2000);
		Account accountThree = new CurrentAccount("3333", 3000.0, 3000);
		Account accountFour = new CurrentAccount("4444", 4000.0, 3000);
		Account accountFive = new CurrentAccount("5555", 5000.0, 3000);

		
		Account accountSix = new SavingsAccount("666", 6000.0, 0.3);
		Account accountSeven = new SavingsAccount("777", 7000.0, 0.4);
		
		Advisor advisorOne = new Advisor("Devendra", "Banhart", "devendra@banhart.com");
		Advisor advisorTwo = new Advisor("Erlend", "Oye", "erlend@oye.com");

		// ~~~~~ ASSIGNATION COMPTE ~~~~~
		clientOne.addAccount(accountOne);
		clientOne.addAccount(accountTwo);
		clientTwo.addAccount(accountThree);
		clientTwo.addAccount(accountFour);
		clientTwo.addAccount(accountFive);
		clientThree.addAccount(accountSix);
		clientThree.addAccount(accountSeven);
		
		// ~~~~~ ASSIGNATION CLIENT ~~~~~
		advisorOne.addClient(clientOne);
		advisorOne.addClient(clientTwo);
		advisorTwo.addClient(clientThree);
		
		// ~~~~~ CASCADE PERSIST  ~~~~~
		advisorDao.save(advisorOne);
		advisorDao.save(advisorTwo);

		
	}

}
