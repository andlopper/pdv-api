package com.andlopper.storeoperationbff.controller;

import com.andlopper.storeoperationbff.model.Customer;
import com.andlopper.storeoperationbff.model.Product;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/bff")
public class BffController {

    private final RestTemplate restTemplate;

    private final String customerApiUrl = "https://customer-api-ehtm.onrender.com/customers";

    private final String productsApiUrl = "";

    private final String pdvApiUrl = "";


    public BffController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        RestTemplate restTemplate = new RestTemplate();

        Customer[] customers = restTemplate.getForObject(customerApiUrl, Customer[].class);

        return Arrays.asList(customers);
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        RestTemplate restTemplate = new RestTemplate();

        Customer customer = restTemplate.getForObject(customerApiUrl + "/" + id, Customer.class);

        return customer;
    }


    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        String productApiUrl = "http://localhost:8081/products";
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity(productApiUrl, Product[].class);
        Product[] products = responseEntity.getBody();

        if (products == null || products.length == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(products);
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
