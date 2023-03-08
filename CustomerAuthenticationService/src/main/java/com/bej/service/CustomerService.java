package com.bej.service;

import com.bej.exception.CustomerAlreadyExists;
import com.bej.domain.Customer;
import com.bej.exception.CustomerNotFoundException;

public interface CustomerService {
    Customer saveUser(Customer customer) throws CustomerAlreadyExists;
    public Customer findByEmailAndPassword(String email,String password) throws CustomerNotFoundException;
}
