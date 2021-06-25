package com.apoorv.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoorv.api.model.Registration;
import com.apoorv.api.service.RegistrationService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService regService;
	
	@PostMapping("/saveRegistration")
	public Registration saveRegistration(@RequestBody Registration reg) {
		Registration regObj = regService.findByEmail(reg.getEmail());
		if (regObj == null) {
			System.out.println("Email doesn't exists.");
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(reg.getPassword());
			reg.setPassword(hashedPassword);
			return regService.saveRegistration(reg);
		}
		System.out.println("Email exists.");
		return null;
	}
	
	@PostMapping("/fetchRegistration")
	public Registration fetchRegistration(@RequestBody String email) {
		return regService.findByEmail(email);
	}
	
	@PostMapping("/updateRegistration")
	public Registration updateRegistration(@RequestBody Registration newRegObject) {
		String oldEmail = newRegObject.getOldEmail();
		if (oldEmail.equalsIgnoreCase(newRegObject.getEmail())) {
			System.out.println("email was not changed in UI");
		} else {
			System.out.println("email was changed in UI");
		}
		Registration updatedObject = null;
		try {
			updatedObject = regService.updateRegistrationByEmail(oldEmail, newRegObject);
			
		} catch (Exception e) {
			System.out.println("exception");
			e.printStackTrace();
		}
		return updatedObject;
	}
	
}
