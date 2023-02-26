/*
 * Author : Ketki Keni
 * Date : 20-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.MenuService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND , reason = "Item Not Found")
public class PizzaNotFoundException extends Exception{
}
