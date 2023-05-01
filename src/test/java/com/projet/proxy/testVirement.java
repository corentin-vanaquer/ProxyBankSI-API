package com.projet.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.projet.proxy.model.CurrentAccount;
import com.projet.proxy.model.Virement;
import com.projet.proxy.repository.CurrentAccountDao;
import com.projet.proxy.service.CurrentAccountService;

@RunWith(MockitoJUnitRunner.class)
class testVirement {

	@Mock
	CurrentAccountDao currentAccountDaoMock = mock(CurrentAccountDao.class);
	@InjectMocks
	CurrentAccountService currentAccountService = new CurrentAccountService(currentAccountDaoMock);

	@Test
	public void testDoTransfer() {
		CurrentAccount senderAccount = new CurrentAccount((long) 1, 1000);
		CurrentAccount receivingAccount = new CurrentAccount((long) 2, 1000);

		Mockito.when(currentAccountDaoMock.findById(senderAccount.getId())).thenReturn(Optional.of(senderAccount));
		Mockito.when(currentAccountDaoMock.findById(receivingAccount.getId()))
				.thenReturn(Optional.of(receivingAccount));
//		System.out.println(senderAccount.getId() + " " + receivingAccount.getId());

		currentAccountService.doTransfert(senderAccount, receivingAccount, 1);
//		System.out.println(senderAccount.getSolde() + " " + receivingAccount.getSolde());

		assertEquals(999.0, senderAccount.getSolde());
		assertEquals(1001.0, receivingAccount.getSolde());
//		System.out.println(senderAccount.getSolde() + " " + receivingAccount.getSolde());

	}

	@Test
	public void testDoVirement() {
		CurrentAccount senderAccount = new CurrentAccount((long) 1, 1000);
		CurrentAccount receivingAccount = new CurrentAccount((long) 2, 500);
		
		Mockito.when(currentAccountDaoMock.findById(senderAccount.getId())).thenReturn(Optional.of(senderAccount));
		Mockito.when(currentAccountDaoMock.findById(receivingAccount.getId()))
				.thenReturn(Optional.of(receivingAccount));
		Virement virement = new Virement(1, 2, 500);
		System.out.println(virement);

		currentAccountService.doVirement(virement);
		System.out.println(senderAccount.getSolde());

		assertEquals(500, senderAccount.getSolde());
		assertEquals(1000, receivingAccount.getSolde());
	}
}
