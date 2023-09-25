package com.andlopper.pdv.api.controller;

import com.andlopper.pdv.api.entity.CustomerEntity;
import com.andlopper.pdv.api.service.BffCustomerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class BffCustomerController {

    private final RestTemplate restTemplate;

    private final BffCustomerService bffCustomerService;

    public BffCustomerController(RestTemplate restTemplate, BffCustomerService bffCustomerService) {
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
    public ResponseEntity saveCustomer(@RequestBody CustomerEntity request) {
        CustomerEntity customerEntity = bffCustomerService.saveCustomer(request);
        return ResponseEntity.ok(customerEntity);
    }
}
