/*
 * Author : Ketki Keni
 * Date : 20-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.controller;

import com.bej.domain.Customer;
import com.bej.domain.Pizza;
import com.bej.exception.CustomerAlreadyExistsException;
import com.bej.exception.CustomerNotFoundException;
import com.bej.exception.PizzaNotFoundException;
import com.bej.service.CustomerPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class CustomerPizzaController {
    CustomerPizzaService customerPizzaService;

    @Autowired
    public CustomerPizzaController(CustomerPizzaService customerPizzaService) {
        this.customerPizzaService = customerPizzaService;
    }

    //Uri : http://localhost:8082/api/v2/register : Method : Post
    @PostMapping("/register")
    public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistsException {
        Customer newCustomer = customerPizzaService.saveCustomer(customer);
        return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
    }

    //Uri : http://localhost:8082/api/v2/customers : Method : Get
    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() throws CustomerNotFoundException {
        List<Customer> customers = customerPizzaService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    //Uri : http://localhost:8082/api/v2/user/ket@gmail.com/pizza: Method : Post
//    @PostMapping("/user/{email}/pizza")
//    public ResponseEntity<?> addPizza(@RequestBody Pizza pizza, @PathVariable String email) {
//
//        return new ResponseEntity<>(customerPizzaService.addPizzaToList(pizza, email), HttpStatus.CREATED);
//    }

    //Uri : http://localhost:8082/api/v2/user/cart/ket@gmail.com: Method : Get

    @GetMapping("/user/cart/{email}")
    public ResponseEntity<?> getAllPizzas(@PathVariable String email){

        return new ResponseEntity<>(customerPizzaService.getAllPizzas(email), HttpStatus.OK);
    }

    //Uri : http://localhost:8082/api/v2/user/addToCart/ket@gmail.com: Method : Post
    @PostMapping("/user/addToCart/{emailId}")
    public ResponseEntity<?> addPizzaToCart(@PathVariable String emailId,@RequestBody Pizza pizza)
    {
        try{
            System.out.println("Pizza: " + pizza);
            Customer customer= customerPizzaService.addPizzaToListByEmailId(emailId,pizza);
            return new ResponseEntity(customer,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Uri : http://localhost:8082/api/v2/user/ket@gmail.com/pizza/3 : Method : Delete
//    @DeleteMapping("/user/{email}/pizza/{pizzaId}")
//    public ResponseEntity<?> deletePizza(@PathVariable String email, @PathVariable String pizzaId) throws PizzaNotFoundException {
//
//        return new ResponseEntity<>(customerPizzaService.deletePizzaFromList(email, pizzaId), HttpStatus.OK);
//    }

//    Uri : http://localhost:8082/api/v2/user/deleteAll/ket@gmail.com : Method : Delete
    @DeleteMapping("/user/deleteAll/{email}")
    public ResponseEntity<?> deletePizzaList(@PathVariable String email) {

        return new ResponseEntity<>(customerPizzaService.deleteAllFromList(email), HttpStatus.OK);
    }

    @DeleteMapping("/user/deleteFromCart/{email}/{pizzaId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String email, @PathVariable int pizzaId) throws PizzaNotFoundException {
        boolean pizza = customerPizzaService.deletePizzaFromList(email, pizzaId);
        if(pizza==true){
            return new ResponseEntity<>(pizza,HttpStatus.OK);
        }
        return new ResponseEntity<String>("Could Not Delete",HttpStatus.NOT_FOUND);
    }
}
