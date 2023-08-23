package com.payment.cycle.infrastructure.service;

import com.payment.cycle.entity.CardEntity;
import com.payment.cycle.entity.ResourceEntity;
import com.payment.cycle.infrastructure.repository.CardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;
    @Transactional
    public CardEntity save(CardEntity cardEntity) {
        return cardRepository.save(cardEntity);
    }
    public Optional<CardEntity> findById(UUID id) {
        return cardRepository.findById(id);
    }

}
