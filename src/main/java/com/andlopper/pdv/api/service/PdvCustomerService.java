package com.andlopper.pdv.api.service;

import com.andlopper.pdv.api.entity.CustomerEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PdvCustomerService {
    private final RestTemplate restTemplate;

    private final String customerApiUrl = "https://customer-api-ehtm.onrender.com/customers/";

    public PdvCustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CustomerEntity> getAllCustomers() {
        CustomerEntity[] customerEntities = restTemplate.getForObject(customerApiUrl, CustomerEntity[].class);

        return Arrays.asList(customerEntities);
    }

    public CustomerEntity getCustomerById(Long id) {
        var customerEntity = restTemplate.getForObject(customerApiUrl + id, CustomerEntity.class);

        return customerEntity;
    }

    public CustomerEntity saveCustomer(CustomerEntity request) {
        var customerEntity = restTemplate.postForObject(customerApiUrl, request, CustomerEntity.class);

        return customerEntity;
    }

    public CustomerEntity updateCustomer(Long id, CustomerEntity request) {
        var customerEntity = restTemplate.getForObject(customerApiUrl + id, CustomerEntity.class);

        customerEntity.setName(request.getName());
        customerEntity.setEmail(request.getEmail());
        customerEntity.setPhone(request.getPhone());

        restTemplate.put(customerApiUrl + id, customerEntity, CustomerEntity.class);

        return customerEntity;
    }


    public CustomerEntity partialUpdateCustomer(Long id, CustomerEntity request) {
        var customerEntity = restTemplate.getForObject(customerApiUrl + id, CustomerEntity.class);

        if (request.getName() != null){
            customerEntity.setName(request.getName());
        }
        if (request.getEmail() != null){
            customerEntity.setEmail(request.getEmail());
        }
        if (request.getPhone() != null){
            customerEntity.setPhone(request.getPhone());
        }

        restTemplate.put(customerApiUrl + id, customerEntity, CustomerEntity.class);

        return customerEntity;
    }
}
