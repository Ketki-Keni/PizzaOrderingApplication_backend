package com.bej.service;

import com.bej.domain.Customer;
import com.bej.domain.Pizza;
import com.bej.exception.CustomerAlreadyExistsException;
import com.bej.exception.CustomerNotFoundException;
import com.bej.exception.PizzaNotFoundException;

import java.util.List;

public interface CustomerPizzaService {
    public Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException;
    public List<Customer> getAllCustomers() throws CustomerNotFoundException;
    public Customer addPizzaToList(Pizza pizza, String email);
//    public Customer addPizzaToList(Pizza pizza, String email);
    public List<Pizza> getAllPizzas(String email);
    public Customer addPizzaToListByEmailId(String emailId, Pizza pizza);

    public boolean deletePizzaFromList(String email, int pizzaId) throws PizzaNotFoundException;

    public Customer deleteAllFromList(String email);
}
