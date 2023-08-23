package com.payment.cycle.infrastructure.service;

import com.payment.cycle.entity.PaymentEntity;
import com.payment.cycle.entity.ResourceEntity;
import com.payment.cycle.infrastructure.repository.ResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;
    @Transactional
    public ResourceEntity save(ResourceEntity resourceEntity) {
        return resourceRepository.save(resourceEntity);
    }
    public Optional<ResourceEntity> findById(UUID id) {
        return resourceRepository.findById(id);
    }
}
