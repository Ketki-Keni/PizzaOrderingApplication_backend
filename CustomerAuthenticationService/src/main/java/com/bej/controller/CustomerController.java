/*
 * Author : Ketki Keni
 * Date : 20-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.controller;

import com.bej.domain.Customer;
import com.bej.exception.CustomerNotFoundException;
import com.bej.service.CustomerServiceImpl;
import com.bej.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private CustomerServiceImpl customerService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService, SecurityTokenGenerator securityTokenGenerator) {
        this.customerService = customerService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    //Uri : http://localhost:8082/api/v1/login : Method : Post
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Customer customer) throws CustomerNotFoundException
    {
        Customer retrievedCustomer = customerService.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
        if(retrievedCustomer ==null)
        {
            throw new CustomerNotFoundException();
        }
        return new ResponseEntity<>(securityTokenGenerator.generateToken(customer), HttpStatus.OK);
    }

}
