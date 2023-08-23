package com.payment.cycle.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TB_RESOURCE")
public class ResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private boolean recurrence;
    private short installments;
    private String statementDescriptor;
    private String paymentMethod;
    private UUID creditCard;
}
