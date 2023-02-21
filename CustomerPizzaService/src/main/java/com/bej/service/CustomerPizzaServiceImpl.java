/*
 * Author : Ketki Keni
 * Date : 20-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service;

import com.bej.domain.Customer;
import com.bej.domain.Pizza;
import com.bej.exception.CustomerAlreadyExistsException;
import com.bej.exception.PizzaNotFoundException;
import com.bej.proxy.CustomerProxy;
import com.bej.repository.CustomerPizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerPizzaServiceImpl implements CustomerPizzaService {

    CustomerProxy customerProxy;
    CustomerPizzaRepository customerPizzaRepository;

    @Autowired
    public CustomerPizzaServiceImpl(CustomerProxy customerProxy, CustomerPizzaRepository customerPizzaRepository) {
        this.customerProxy = customerProxy;
        this.customerPizzaRepository = customerPizzaRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException {
        Customer newCustomer = customerPizzaRepository.save(customer);
        System.out.println(newCustomer);
        if (!(newCustomer.getEmail().isEmpty())){
            ResponseEntity responseEntity=customerProxy.saveCustomerInAuthService(newCustomer);
            System.out.println(responseEntity.getBody());
        }
        return newCustomer;
    }

    @Override
    public Customer addPizzaToList(Pizza pizza, String email) {
        Customer customer = customerPizzaRepository.findById(email).get();
        if(customer.getPizzaList() == null)
        {
            customer.setPizzaList(Arrays.asList(pizza));
        }
        else {
            List<Pizza> pizzas = customer.getPizzaList();
            pizzas.add(pizza);
            customer.setPizzaList(pizzas);
        }
        return customerPizzaRepository.save(customer);
    }

    @Override
    public List<Pizza> getAllPizzas(String email) {
        return  customerPizzaRepository.findById(email).get().getPizzaList();

    }

    @Override
    public Customer deletePizzaFromList(String email, String pizzaId) throws PizzaNotFoundException {
        boolean PizzaIdIsPresent = false;
        Customer customer = customerPizzaRepository.findById(email).get();
        List<Pizza> pizzas = customer.getPizzaList();
        PizzaIdIsPresent = pizzas.removeIf(x->x.getId().equals(pizzaId));
        if(!PizzaIdIsPresent)
        {
            throw new PizzaNotFoundException();
        }
        customer.setPizzaList(pizzas);
        return customerPizzaRepository.save(customer);
    }
}
