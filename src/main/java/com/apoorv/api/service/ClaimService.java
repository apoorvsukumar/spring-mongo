package com.apoorv.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apoorv.api.model.ClaimForm;
import com.apoorv.api.repository.ClaimRepository;

@Service
public class ClaimService {
	@Autowired
	private ClaimRepository claimRepository;
	
	public ClaimForm updateClaimFormById(int id, ClaimForm form) {
		Optional<ClaimForm> d = claimRepository.findById(id);
		ClaimForm fromDb = d.get();
		fromDb.setFirstName(form.getFirstName());
		fromDb.setLastName(form.getLastName());
		fromDb.setAdmissionDate(form.getAdmissionDate());
		fromDb.setDependentType(form.getDependentType());
		fromDb.setDischargeDate(form.getDischargeDate());
		fromDb.setDob(form.getDob());
		fromDb.setProviderName(form.getProviderName());
		fromDb = claimRepository.save(fromDb);
		return fromDb;
	}
}
