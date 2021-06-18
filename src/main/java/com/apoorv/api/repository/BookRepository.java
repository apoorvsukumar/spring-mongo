package com.apoorv.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.apoorv.api.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {

}
