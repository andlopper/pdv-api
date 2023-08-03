package com.andlopper.storeoperationbff.service;

import com.andlopper.storeoperationbff.model.Customer;
import com.andlopper.storeoperationbff.model.Pdv;
import com.andlopper.storeoperationbff.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BffService {
    private final RestTemplate restTemplate;

    @Autowired
    public BffService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductById(Long productId) {
        String productApiUrl = "localhost:8081" + productId;
        return restTemplate.getForObject(productApiUrl, Product.class);
    }

    public Product getProducts(){
        String productApiUrl = "localhost:8081";
        return restTemplate.getForObject(productApiUrl, Product.class);
    }

    public Pdv getPdvById(Long pdvId) {
        String pdvApiUrl = "http://example.com/pdv-api/pdvs/" + pdvId;
        return restTemplate.getForObject(pdvApiUrl, Pdv.class);
    }

    public Customer getCustomerById(Long customerId) {
        String customerApiUrl = "http://example.com/customer-api/customers/" + customerId;
        return restTemplate.getForObject(customerApiUrl, Customer.class);
    }
}
