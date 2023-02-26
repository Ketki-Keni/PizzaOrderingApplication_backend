package com.bej.repository;

import com.bej.domain.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaRepository extends MongoRepository<Pizza,Integer> {
}
