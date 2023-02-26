/*
 * Author : Ketki Keni
 * Date : 22-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.MenuService.service;

import com.bej.MenuService.domain.Pizza;
import com.bej.MenuService.exception.PizzaNotFoundException;
import com.bej.MenuService.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    PizzaRepository pizzaRepository;

    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza addPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza getPizza(String pizzaId) throws PizzaNotFoundException {
        return pizzaRepository.findById(pizzaId).get();
    }
}
