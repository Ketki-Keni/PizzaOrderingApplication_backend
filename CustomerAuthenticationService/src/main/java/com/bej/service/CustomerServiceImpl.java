/*
 * Author : Ketki Keni
 * Date : 20-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service;

import com.bej.exception.CustomerAlreadyExists;
import com.bej.domain.Customer;
import com.bej.exception.CustomerNotFoundException;
import com.bej.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveUser(Customer customer) throws CustomerAlreadyExists {
        if(customerRepository.findById(customer.getEmail()).isPresent())
        {
            throw new CustomerAlreadyExists();
        }
        System.out.println(customer);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findByEmailAndPassword(String email, String password) throws CustomerNotFoundException {
        System.out.println("email: "+email);
        System.out.println("password: "+password);
        Customer loggedInUser = customerRepository.findByEmailAndPassword(email,password);
        System.out.println(loggedInUser);
        if(loggedInUser == null)
        {
            throw new CustomerNotFoundException();
        }
        return loggedInUser;
    }
}
