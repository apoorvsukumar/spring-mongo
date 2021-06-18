package com.apoorv.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.apoorv.api.model.ClaimForm;

public interface ClaimRepository extends MongoRepository<ClaimForm, String> {

}
