/*
 * Author : Ketki Keni
 * Date : 20-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service;

import com.bej.domain.Customer;
import com.bej.domain.Pizza;
import com.bej.exception.CustomerAlreadyExistsException;
import com.bej.exception.CustomerNotFoundException;
import com.bej.exception.PizzaNotFoundException;
import com.bej.proxy.CustomerProxy;
import com.bej.repository.CustomerPizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public List<Customer> getAllCustomers() throws CustomerNotFoundException {
        return customerPizzaRepository.findAll();
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
    public Customer addPizzaToListByEmailId(String emailId, Pizza pizza) {
        Customer customer= customerPizzaRepository.findById(emailId).get();
        if(customer.getPizzaList() == null)
        {
            customer.setPizzaList(Arrays.asList(pizza));
        }
        else
        {
            customer.getPizzaList().add(pizza);
        }
        return  customerPizzaRepository.save(customer);
    }

//    @Override
//    public Customer deletePizzaFromList(String email, String pizzaId) throws PizzaNotFoundException {
//        boolean PizzaIdIsPresent = false;
//        Customer customer = customerPizzaRepository.findById(email).get();
//        List<Pizza> pizzas = customer.getPizzaList();
//        PizzaIdIsPresent = pizzas.removeIf(x->x.getId().equals(pizzaId));
//        if(!PizzaIdIsPresent)
//        {
//            throw new PizzaNotFoundException();
//        }
//        customer.setPizzaList(pizzas);
//        return customerPizzaRepository.save(customer);
//    }
    @Override
    public Customer deleteAllFromList(String email) {
        Customer customer = customerPizzaRepository.findById(email).get();
        List<Pizza> pizzaList = customer.getPizzaList();
//        pizzaList.removeAll(pizzaList);
        boolean pizzaList1  = pizzaList.removeAll(pizzaList);
//        customer.setPizzaList(pizzaList);
        return customerPizzaRepository.save(customer);
    }

    @Override
    public boolean deletePizzaFromList(String email, int pizzaId) throws PizzaNotFoundException {
        Optional<Customer> byId = customerPizzaRepository.findById(email);
        if(byId.isPresent()) {
            System.out.println("id"+byId);
            Customer customer = byId.get();
            System.out.println(customer);
            List<Pizza> pizzaList = customer.getPizzaList();
            Pizza pizza = pizzaList.stream().filter(product -> product.getId() == pizzaId).findFirst().orElse(null);
            System.out.println(pizza);
            if(pizza!=null) {
                boolean remove = pizzaList.remove((pizza));
                customerPizzaRepository.save(customer);
                return true;
            }
        }
        return false;
    }
}
