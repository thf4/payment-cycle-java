package com.payment.cycle.infrastructure.repository;

import com.payment.cycle.domain.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    Optional<CardEntity> findCardByNumber(String cardNumber);
}
