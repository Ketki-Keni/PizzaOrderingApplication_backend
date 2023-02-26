package com.bej.MenuService.service;

import com.bej.MenuService.domain.Pizza;
import com.bej.MenuService.exception.PizzaNotFoundException;

import java.util.List;

public interface PizzaService {
    public Pizza addPizza(Pizza track);
    public List<Pizza> getAllPizzas();
    public Pizza getPizza(String pizzaId) throws PizzaNotFoundException;

//    public boolean deletePizza(String pizzaId);
//    public Pizza updatePizzaDetails(Pizza track,String pizzaId);
}
