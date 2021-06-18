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

@RestController
@CrossOrigin("http://localhost:4200")
public class BookController {
	
	
	@Autowired
	private ClaimRepository claimRepository;
	
	@PostMapping("/addClaim")
	public ClaimForm saveClaim(@RequestBody ClaimForm claimForm) {
		ClaimForm c = claimRepository.save(claimForm);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c.getFirstName());
		return c;
	}
	
	@GetMapping("/findAllClaims")
	public List<ClaimForm> getAllClaim() {
		return claimRepository.findAll();
	}
	
	@DeleteMapping("/deleteClaim/{id}")
	public List<ClaimForm> deleteClaim(@PathVariable String id) {
		claimRepository.deleteById(id);
		return getAllClaim();
	}
	
	@PostMapping("/fetchClaimById")
	public Optional<ClaimForm> fetchClaimById(@RequestBody String id) {
		System.out.println("came here");
		return claimRepository.findById(id);
	}
	
	@PostMapping("/updateClaimById")
	public String updateClaimById(@RequestBody ClaimForm updatableForm) {
		System.out.println("Updating...");
		Optional<ClaimForm> c = claimRepository.findById(updatableForm.getId());
		if (c.isPresent()) {
			ClaimForm existingForm = c.get();
			existingForm.setFirstName(updatableForm.getFirstName());
			existingForm.setLastName(updatableForm.getLastName());
			existingForm.setAdmissionDate(updatableForm.getAdmissionDate());
			existingForm.setDependentType(updatableForm.getDependentType());
			existingForm.setDischargeDate(updatableForm.getDischargeDate());
			existingForm.setDob(updatableForm.getDob());
			existingForm.setProviderName(updatableForm.getProviderName());
			existingForm = claimRepository.save(existingForm);
			System.out.println(existingForm.getProviderName());
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Claim updated successfully";
	}
	
	@RequestMapping("/validateLogin")
	public User validateLogin() {
		return new User("User successfully activated");
	}
}
