package com.payment.cycle.entity;

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
    private String number;
    private String  holderName;
    private short expMonth;
    private short expYear;
    private String cvv;
    private UUID address;
    private UUID customer;



}