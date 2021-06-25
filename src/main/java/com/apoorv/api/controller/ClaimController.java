package com.apoorv.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apoorv.api.model.ClaimForm;
import com.apoorv.api.model.User;
import com.apoorv.api.repository.ClaimRepository;
import com.apoorv.api.service.ClaimService;
import com.apoorv.api.service.NextSequenceService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private ClaimRepository claimRepository;
	
	@Autowired
	private NextSequenceService nextSequenceService;
	
	@PostMapping("/addClaim")
	public ClaimForm saveClaim(@RequestBody ClaimForm claimForm) {
		claimForm.setId(nextSequenceService.getNextSequence("customSequences"));
		ClaimForm c = claimService.saveClaim(claimForm);
		return c;
	}
	
	@GetMapping("/findAllClaims")
	public List<ClaimForm> getAllClaim() {
		return claimService.getAllClaim();
	}
	
	@DeleteMapping("/deleteClaim/{id}")
	public List<ClaimForm> deleteClaim(@PathVariable String id) {
		claimService.deleteClaimById(id);
		return getAllClaim();
	}
	
	@PostMapping("/fetchClaimById")
	public ClaimForm fetchClaimById(@RequestBody String id) {
		return claimService.fetchClaimById(id).get();
	}
	
	@PostMapping("/updateClaimById")
	public ClaimForm updateClaimById(@RequestBody ClaimForm updatableForm) {
		System.out.println("Updating...");
		ClaimForm form = null;
		try {
			form = claimService.updateClaimFormById(updatableForm.getId(), updatableForm);
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return form;
	}
	
	@RequestMapping("/validateLogin")
	public User validateLogin() {
		return new User("User successfully activated");
	}
}
