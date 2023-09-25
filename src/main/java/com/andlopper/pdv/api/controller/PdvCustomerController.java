package com.andlopper.pdv.api.controller;

import com.andlopper.pdv.api.entity.CustomerEntity;
import com.andlopper.pdv.api.service.PdvCustomerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class PdvCustomerController {

    private final RestTemplate restTemplate;

    private final PdvCustomerService pdvCustomerService;

    public PdvCustomerController(RestTemplate restTemplate, PdvCustomerService pdvCustomerService) {
        this.restTemplate = restTemplate;
        this.pdvCustomerService = pdvCustomerService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<CustomerEntity> customerEntities = pdvCustomerService.getAllCustomers();

        return ResponseEntity.ok(customerEntities);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") Long id) {
        CustomerEntity customerEntity = pdvCustomerService.getCustomerById(id);

        return ResponseEntity.ok(customerEntity);
    }

    @PostMapping
    public ResponseEntity saveCustomer(@RequestBody CustomerEntity request) {
        CustomerEntity customerEntity = pdvCustomerService.saveCustomer(request);
        return ResponseEntity.ok(customerEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable("id") Long id,
                                         @RequestBody CustomerEntity request) {
        CustomerEntity customerEntity = pdvCustomerService.updateCustomer(id, request);

        return ResponseEntity.ok(customerEntity);
    }

    @PatchMapping("/{id}")
    public ResponseEntity partialUpdateCustomer(@PathVariable("id") Long id,
                                                @RequestBody CustomerEntity request) {
        CustomerEntity customerEntity = pdvCustomerService.partialUpdateCustomer(id, request);

        return ResponseEntity.ok(customerEntity);
    }
}
