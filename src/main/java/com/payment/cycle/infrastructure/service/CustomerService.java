package com.payment.cycle.infrastructure.service;

import com.payment.cycle.domain.entity.CustomerEntity;
import com.payment.cycle.infrastructure.exceptions.GenericException;
import com.payment.cycle.infrastructure.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public void save(CustomerEntity customer) {customerRepository.save(customer);}
    public CustomerEntity findById(UUID id) throws GenericException {
        return customerRepository.findById(id).
                orElseThrow(() -> new GenericException("Customer has not found!"));
    }

    public Optional<CustomerEntity> findCustomerByCpf(String cpf) {
        return customerRepository.findCustomerByCpf(cpf);
    }

    public List<CustomerEntity> findAll() {return customerRepository.findAll(); }
}
