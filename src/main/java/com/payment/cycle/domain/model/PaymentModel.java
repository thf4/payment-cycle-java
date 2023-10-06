package com.payment.cycle.domain.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentModel {
    private CustomerModel customer;
    private BigDecimal amount;
    private String description;
    private ResourceModel resource;
}

