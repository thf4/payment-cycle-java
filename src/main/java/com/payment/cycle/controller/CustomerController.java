package com.payment.cycle.controller;

import com.payment.cycle.domain.entity.CustomerEntity;
import com.payment.cycle.domain.entity.PaymentEntity;
import com.payment.cycle.domain.model.CustomerModel;
import com.payment.cycle.infrastructure.exceptions.GenericException;
import com.payment.cycle.infrastructure.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) throws RuntimeException, GenericException {
        var customer = customerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerEntity> list() throws RuntimeException {
        return customerService.findAll();
    }
}
