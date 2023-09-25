package com.andlopper.storeoperationbff.service;

import com.andlopper.storeoperationbff.entity.CustomerEntity;
import com.andlopper.storeoperationbff.entity.PdvEntity;
import com.andlopper.storeoperationbff.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class BffService {
    private final RestTemplate restTemplate;

    private final String customerApiUrl = "https://customer-api-ehtm.onrender.com/customers/";

    private final String productsApiUrl = "";

    private final String pdvApiUrl = "";

    public BffService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CustomerEntity> getAllCustomers() {
        CustomerEntity[] customerEntities = restTemplate.getForObject(customerApiUrl, CustomerEntity[].class);

        return Arrays.asList(customerEntities);
    }

    public CustomerEntity getCustomerById(Long id) {
        CustomerEntity customerEntity = restTemplate.getForObject(customerApiUrl + id, CustomerEntity.class);

        return customerEntity;
    }
}
