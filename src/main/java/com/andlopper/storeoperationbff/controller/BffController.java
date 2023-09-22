package com.andlopper.storeoperationbff.controller;

import com.andlopper.storeoperationbff.entity.CustomerEntity;
import com.andlopper.storeoperationbff.entity.ProductEntity;
import com.andlopper.storeoperationbff.service.BffService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/bff")
public class BffController {

    private final RestTemplate restTemplate;

    private final BffService bffService;

    public BffController(RestTemplate restTemplate, BffService bffService) {
        this.restTemplate = restTemplate;
        this.bffService = bffService;
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        List<CustomerEntity> customerEntities = bffService.getAllCustomers();

        return ResponseEntity.ok(customerEntities);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") Long id) {
        CustomerEntity customerEntity = bffService.getCustomerById(id);

        return ResponseEntity.ok(customerEntity);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        String productApiUrl = "http://localhost:8081/products";
        ResponseEntity<ProductEntity[]> responseEntity = restTemplate.getForEntity(productApiUrl, ProductEntity[].class);
        ProductEntity[] productEntities = responseEntity.getBody();

        if (productEntities == null || productEntities.length == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productEntities);
    }

    @GetMapping("/products/{productId}")
    public String getProductById(@PathVariable Long productId) {
        String productApiUrl = "http://localhost:8081/products/" + productId;
        return restTemplate.getForObject(productApiUrl, String.class);
    }

    @GetMapping("/pdv")
    public String getPDVData() {
        String pdvApiUrl = "http://pdv-api/api/pdvs"; // Substitua pela URL da pdv-api
        return restTemplate.getForObject(pdvApiUrl, String.class);
    }

    @GetMapping("/sales/{saleId}")
    public String getSaleById(@PathVariable Long saleId) {
        String saleApiUrl = "http://localhost:8082/sales/" + saleId;
        return restTemplate.getForObject(saleApiUrl, String.class);
    }
}
