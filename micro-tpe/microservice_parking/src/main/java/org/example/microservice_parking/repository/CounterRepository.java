package org.example.microservice_parking.repository;

import org.example.microservice_parking.entities.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends MongoRepository<Counter, String> {
    Optional<Counter> findById(String id);


}
