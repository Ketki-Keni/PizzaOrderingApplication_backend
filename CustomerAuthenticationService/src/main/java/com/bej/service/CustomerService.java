package com.bej.service;

import com.bej.domain.Customer;
import com.bej.exception.CustomerNotFoundException;

public interface CustomerService {
    public Customer findByEmailAndPassword(String email,String password) throws CustomerNotFoundException;
}
