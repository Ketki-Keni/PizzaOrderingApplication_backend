/*
 * Author : Ketki Keni
 * Date : 21-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Customer already exists")
public class CustomerAlreadyExists extends Exception {
}
