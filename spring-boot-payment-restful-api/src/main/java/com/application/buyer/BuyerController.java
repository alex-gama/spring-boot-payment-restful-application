package com.application.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyers")
public class BuyerController {
	
	@Autowired
	private BuyerRepository repository;

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Buyer> findBy(@PathVariable("id") Long id) {
		Buyer buyer = repository.findOne(id);
		
		return ResponseEntity.ok(buyer);
	}
	
}
