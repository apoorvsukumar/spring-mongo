package com.apoorv.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoorv.api.model.Registration;
import com.apoorv.api.repository.RegistrationRepository;


public class RegistrationController {
	
	@Autowired
	private RegistrationRepository regRepo;
	
	@PostMapping("/saveRegistration")
	public Registration saveRegistration(@RequestBody Registration reg) {
		return regRepo.save(reg);
	}
}