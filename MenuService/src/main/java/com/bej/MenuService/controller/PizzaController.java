/*
 * Author : Ketki Keni
 * Date : 22-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.MenuService.controller;

import com.bej.MenuService.domain.Pizza;
import com.bej.MenuService.exception.PizzaNotFoundException;
import com.bej.MenuService.service.PizzaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v3")
public class PizzaController {
    PizzaServiceImpl pizzaService;

    @Autowired
    public PizzaController(PizzaServiceImpl pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping("/admin/pizza")
    public ResponseEntity<?> addCustomer(@RequestBody Pizza pizza){

        Pizza newPizza = pizzaService.addPizza(pizza);

        if(newPizza!=null){
            return new ResponseEntity<Pizza>(newPizza, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>("Error Occurred While Inserting",HttpStatus.NOT_FOUND);
        }
    }


    //Uri : http://localhost:8084/api/v3/user/Pizzas : Method : Get
    @GetMapping("/user/Pizzas")
    public ResponseEntity<?> getAllPizzas(){
        List<Pizza> allPizzas =pizzaService.getAllPizzas();

        if(allPizzas.size()>0){
            return new ResponseEntity<List<Pizza>>(allPizzas, HttpStatus.ACCEPTED.OK);
        }else {
            return new ResponseEntity<String>("No Items Found",HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8084/api/user/v3/Pizza/2 : Method : Get

    @GetMapping("/user/Pizza/{pizzaId}")
    public ResponseEntity<?> getTrack(@PathVariable String pizzaId) throws PizzaNotFoundException {
        Pizza pizza=pizzaService.getPizza(pizzaId);
        if(pizza!=null){
            return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
        }else{
            throw new PizzaNotFoundException();
        }
    }
}
