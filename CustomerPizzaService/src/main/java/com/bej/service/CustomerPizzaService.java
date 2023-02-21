package com.bej.service;

import com.bej.domain.Customer;
import com.bej.domain.Pizza;
import com.bej.exception.CustomerAlreadyExistsException;
import com.bej.exception.PizzaNotFoundException;

import java.util.List;

public interface CustomerPizzaService {
    public Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException;
    public Customer addPizzaToList(Pizza pizza, String email);
    public List<Pizza> getAllPizzas(String email);
    public Customer deletePizzaFromList(String email, String pizzaId) throws PizzaNotFoundException;

}
