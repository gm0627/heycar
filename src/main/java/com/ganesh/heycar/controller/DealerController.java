package com.ganesh.heycar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ganesh.heycar.exception.EntityNotFoundException;
import com.ganesh.heycar.model.Dealer;
import com.ganesh.heycar.service.DealerService;

@RestController
public class DealerController {

	

	@Autowired
	DealerService dealerService;

	@GetMapping("/dealers")
	@ResponseBody
	public List<Dealer> getAllDealers() {
		
		return dealerService.findAllDealers();
	}

	@PostMapping("/dealers")
	public void adddealer(@Validated @RequestBody List<Dealer> dealerListing) {
		dealerService.updateDealers(dealerListing);
	}

	@GetMapping("/dealer/{dealerId}")
	@ResponseBody
	public Dealer getDealerByID(@PathVariable(required = true)long dealerId) {
		 
		Dealer dealer=dealerService.findDealerByID(dealerId);
		if(dealer==null) {
			throw new EntityNotFoundException("Dealer not found!");
		}
		return dealer;
	}


}
