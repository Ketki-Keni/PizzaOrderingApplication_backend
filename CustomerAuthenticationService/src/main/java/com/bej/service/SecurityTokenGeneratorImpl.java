/*
 * Author : Ketki Keni
 * Date : 13-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service;

import com.bej.domain.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(Customer customer) {
        Map<String, String> tokenMap= new HashMap<String,String>();
        customer.setPassword("");
        Map<String, Object> customerData = new HashMap<>();
        customerData.put("userEmail",customer.getEmail());

        String jwtTokenString = Jwts.builder()
                .setClaims(customerData)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "mySecretKey").compact();

        tokenMap.put("token",jwtTokenString);
        tokenMap.put("message","Login Successful");
        return tokenMap;
    }
}
