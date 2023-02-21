/*
 * Author : Ketki Keni
 * Date : 20-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document
public class Customer {
    private String firstName;
    private String lastName;
    @Id
    private String email;
    private String password;
    private String mobileNumber;
    private String address;
    private List<Pizza> pizzaList;
}
