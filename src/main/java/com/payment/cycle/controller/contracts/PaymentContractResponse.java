package com.payment.cycle.controller.contracts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentContractResponse {
    private double amount;
    private String description;
    private String paymentStatus;
    private CustomerContract customer;
    private ResourceContract resource;
}
