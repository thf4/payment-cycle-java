package com.payment.cycle.infrastructure.repository;

import com.payment.cycle.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourceRepository extends JpaRepository<ResourceEntity, UUID> {
}
