package com.bej.proxy;

import com.bej.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient
(name="customer-authentication-service",url="localhost:8083")
public interface CustomerProxy {
    @PostMapping("/api/v1/customer")
    public ResponseEntity<?> saveCustomerInAuthService(@RequestBody Customer customer);
}
