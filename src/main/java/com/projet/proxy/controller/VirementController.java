package com.projet.proxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

//	@PostMapping
//	public ResponseEntity<String> doTransfert(@RequestParam("senderId") long senderId, @RequestParam("receiverId") long receiverId,
//			@RequestParam("amount") double amount) {
//		try {
//			currentAccountService.doVirement(senderId, receiverId, amount);
//			return ResponseEntity.ok("Virement effectué avec succès");
//		} catch (IllegalArgumentException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
	
	@PostMapping
	public void doTransfert(@RequestBody Virement virement) {
//		System.out.println(virement);
//		try {
			currentAccountService.doVirement(virement);
			

//		} catch (IllegalArgumentException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
	}

}
