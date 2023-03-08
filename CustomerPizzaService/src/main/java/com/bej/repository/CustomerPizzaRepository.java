package com.bej.repository;

import com.bej.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPizzaRepository extends MongoRepository<Customer, String> {

}
