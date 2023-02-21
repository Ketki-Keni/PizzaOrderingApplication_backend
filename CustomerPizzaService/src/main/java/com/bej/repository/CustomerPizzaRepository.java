package com.bej.repository;

import com.bej.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerPizzaRepository extends MongoRepository<Customer, String> {

}
