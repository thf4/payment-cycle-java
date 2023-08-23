package com.payment.cycle.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "TB_PAYMENTS")
public class PaymentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID customer;
    private double amount;
    private UUID resource;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    private LocalDateTime updateDate;
    private String description;
    @Column(nullable = false)
    private String paymentStatus;
}
