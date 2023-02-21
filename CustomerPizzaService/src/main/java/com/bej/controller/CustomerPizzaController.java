/*
 * Author : Ketki Keni
 * Date : 20-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.controller;

import com.bej.domain.Customer;
import com.bej.domain.Pizza;
import com.bej.exception.CustomerAlreadyExistsException;
import com.bej.exception.PizzaNotFoundException;
import com.bej.repository.CustomerPizzaRepository;
import com.bej.service.CustomerPizzaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class CustomerPizzaController {
    CustomerPizzaServiceImpl customerPizzaService;
    private final CustomerPizzaRepository customerPizzaRepository;

    @Autowired
    public CustomerPizzaController(CustomerPizzaServiceImpl customerPizzaService, CustomerPizzaRepository customerPizzaRepository) {
        this.customerPizzaService = customerPizzaService;
        this.customerPizzaRepository = customerPizzaRepository;
    }

    //Uri : http://localhost:8083/api/v2/register : Method : Post
    @PostMapping("/register")
    public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistsException {
        Customer newCustomer = customerPizzaService.saveCustomer(customer);
        return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
    }

    //Uri : http://localhost:8083/api/v2/user/ket@gmail.com/pizza: Method : Post
    @PostMapping("/user/{email}/pizza")
    public ResponseEntity<?> addTrack(@RequestBody Pizza pizza, @PathVariable String email) throws TrackAlreadyExistsException {

        return new ResponseEntity<>(customerPizzaService.addPizzaToList(pizza, email), HttpStatus.CREATED);
    }

    //Uri : http://localhost:8083/api/v2/user/ket@gmail.com/pizzas: Method : Get

    @GetMapping("/user/{email}/pizzas")
    public ResponseEntity<?> getAllTracks(@PathVariable String email){

        return new ResponseEntity<>(customerPizzaService.getAllPizzas(email), HttpStatus.OK);
    }

    //Uri : http://localhost:8083/api/v2/user/ket@gmail.com/pizza/3 : Method : Delete
    @DeleteMapping("/user/{email}/pizza/{pizzaId}")
    public ResponseEntity<?> deleteTrack(@PathVariable String email, @PathVariable String pizzaId) throws TrackNotFoundException, PizzaNotFoundException {

        return new ResponseEntity<>(customerPizzaService.deletePizzaFromList(email, pizzaId), HttpStatus.OK);
    }
}
