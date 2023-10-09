package com.payment.cycle.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TB_PAYMENTS")
public class PaymentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
    @Column(nullable = false)
    private BigDecimal amount;
    @Embedded
    private ResourceEntity resource;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @Column(nullable = false)
    private LocalDateTime updateDate;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String paymentStatus;
}
