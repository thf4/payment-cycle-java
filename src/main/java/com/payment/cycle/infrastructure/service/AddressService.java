package com.payment.cycle.infrastructure.service;

import com.payment.cycle.entity.AddressEntity;
import com.payment.cycle.entity.CustomerEntity;
import com.payment.cycle.infrastructure.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    @Transactional
    public AddressEntity save(AddressEntity addressEntity) {
        return addressRepository.save(addressEntity);
    }

    public Optional<AddressEntity> findById(UUID id) {
        return addressRepository.findById(id);
    }
}
