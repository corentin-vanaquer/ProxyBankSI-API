package com.projet.proxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.proxy.model.Virement;
import com.projet.proxy.service.ICurrentAccountService;

@RestController
@RequestMapping("/virement")
public class VirementController {

	@Autowired
	ICurrentAccountService currentAccountService;

	/**
	 * 
	 * This method performs a transfer between two bank accounts using the given
	 * Virement object. The current account service is used to execute the transfer.
	 * 
	 * @param virement The Virement object containing the necessary information for
	 *                 the transfer.
	 * @return void
	 */
	@PostMapping
	public void doTransfert(@RequestBody Virement virement) {
		currentAccountService.doVirement(virement);
	}

}
