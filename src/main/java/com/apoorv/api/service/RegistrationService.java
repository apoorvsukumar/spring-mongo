package com.apoorv.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apoorv.api.model.Registration;
import com.apoorv.api.repository.RegistrationRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository repo;

	public Registration saveRegistration(Registration reg) {
		return repo.save(reg);
	}

	public Registration findByEmail(String email) {
		System.out.println("hitting db : "+email);
		return repo.findByEmail(email);
	}

	public Registration updateRegistrationByEmail(String oldEmail, Registration formFromUI) {
		Registration d = findByEmail(oldEmail);
		d.setName(formFromUI.getName());
		d.setAddress(formFromUI.getAddress());
		d.setState(formFromUI.getState());
		d.setEmail(formFromUI.getEmail());
		d.setContactNo(formFromUI.getContactNo());
		d.setPan(formFromUI.getPan());
		d = repo.save(d);
		return d;
	}

}
