package com.andlopper.storeoperationbff.controller;

import com.andlopper.storeoperationbff.entity.CustomerEntity;
import com.andlopper.storeoperationbff.service.BffCustomerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final RestTemplate restTemplate;

    private final BffCustomerService bffCustomerService;

    public CustomerController(RestTemplate restTemplate, BffCustomerService bffCustomerService) {
        this.restTemplate = restTemplate;
        this.bffCustomerService = bffCustomerService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<CustomerEntity> customerEntities = bffCustomerService.getAllCustomers();

        return ResponseEntity.ok(customerEntities);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") Long id) {
        CustomerEntity customerEntity = bffCustomerService.getCustomerById(id);

        return ResponseEntity.ok(customerEntity);
    }

    @PostMapping
}
