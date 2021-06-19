package com.apoorv.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.apoorv.api.model.Registration;

public interface RegistrationRepository extends MongoRepository<Registration, String>{
	Registration findByEmail(String email);
}
