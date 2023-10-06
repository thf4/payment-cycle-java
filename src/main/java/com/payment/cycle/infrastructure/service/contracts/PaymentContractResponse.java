package com.payment.cycle.infrastructure.service.contracts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PaymentContractResponse {
    private BigDecimal amount;
    private String description;
    private String paymentStatus;
    private CustomerContract customer;
    private ResourceContract resource;
}
