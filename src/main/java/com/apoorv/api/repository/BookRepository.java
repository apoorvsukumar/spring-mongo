package com.apoorv.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.apoorv.api.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {
	
}
