package com.apoorv.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.apoorv.api.model.ClaimForm;

@Repository
public interface ClaimRepository extends MongoRepository<ClaimForm, Integer> {
	List<ClaimForm> findByFirstName(String firstName);
}
