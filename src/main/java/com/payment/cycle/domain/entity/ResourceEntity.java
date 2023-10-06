package com.payment.cycle.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ResourceEntity {
    @Column(nullable = false)
    private Boolean recurrence;
    @Column(nullable = false)
    private Short installments;
    @Column(nullable = false)
    private String statementDescriptor;
    @Column(nullable = false)
    private String paymentMethod;
    @OneToOne
    @JoinColumn(name = "credit_card_id")
    private CardEntity creditCard;
}
