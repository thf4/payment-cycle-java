package com.payment.cycle.infrastructure.service;

import com.payment.cycle.domain.entity.CustomerEntity;
import com.payment.cycle.infrastructure.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public CustomerEntity save(CustomerEntity customer) { return customerRepository.save(customer); }
    public Optional<CustomerEntity> findById(UUID id) {
        return customerRepository.findById(id);
    }

}
