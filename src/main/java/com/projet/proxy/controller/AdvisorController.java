package com.projet.proxy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.proxy.model.Advisor;
import com.projet.proxy.service.IAdvisorService;

@RestController
@RequestMapping("/advisors")
public class AdvisorController {
	private IAdvisorService advisor;
	
public AdvisorController(IAdvisorService advisorService) {
		this.advisor = advisorService;
	}
// Get list of all advisors
@GetMapping
public ResponseEntity<List<Advisor>> listAdvisors(){
	List<Advisor> advisors = new ArrayList<>();
	advisors.addAll(advisor.getAllAdvisors());
	if(advisors.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<>(advisors, HttpStatus.OK);
}

}
