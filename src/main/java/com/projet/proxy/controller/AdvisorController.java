package com.projet.proxy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.proxy.model.Advisor;
import com.projet.proxy.service.AdvisorService;

@RestController
@RequestMapping("/advisors")
public class AdvisorController {

	private AdvisorService advisorService;
	
public AdvisorController(AdvisorService advisorService) {
		this.advisorService = advisorService;

	}
// Get list of all advisors
@GetMapping
public ResponseEntity<List<Advisor>> listAdvisors(){
	List<Advisor> advisors = new ArrayList<>();
	advisors.addAll(advisorService.getAllAdvisors());
	if(advisors.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<>(advisors, HttpStatus.OK);
}

// Create new advisor
@PostMapping
public ResponseEntity<Advisor> saveAdvisor(@RequestBody Advisor a){
	Advisor newAdvisor = advisorService.saveAdvisor(a);
	System.out.println(newAdvisor);
	if(newAdvisor == null) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}else {
		return new ResponseEntity<>(newAdvisor, HttpStatus.CREATED);
		
	}
}

// get advisor by Id
@GetMapping("/{id}")
public ResponseEntity<Advisor> getAdvisorById(@PathVariable Long id){
	 Optional<Advisor> advisor = advisorService.getById(id);
	 if(advisor.isPresent()) {
	return new ResponseEntity<>(advisor.get(), HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}
}

// update advisor
@PutMapping
public ResponseEntity<Advisor> updateAdvisor(@RequestBody Advisor a){
	if(advisorService.advisorIdExist(a.getId())) {
		Advisor advisorUpdated = advisorService.updateAdvisor(a);
		return ResponseEntity.status(HttpStatus.OK).body(advisorUpdated);
	}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}

//delete advisor 
@DeleteMapping("/{id}")
public void deleteAdvisor(@PathVariable long id){
	if(advisorService.getById(id)==null) {
		new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
	}
	advisorService.deleteById(id);
}

}
