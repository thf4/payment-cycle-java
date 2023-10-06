package com.payment.cycle.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TB_CARD")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String  holderName;
    @Column(nullable = false)
    private Short expMonth;
    @Column(nullable = false)
    private Short expYear;
    @Column(nullable = false)
    private String cvv;
    @Column(nullable = false)
    @Embedded
    private AddressEntity address;
}