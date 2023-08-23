package com.payment.cycle.controller.contracts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResourceContract {
    private boolean recurrence;
    private short installments;
    private String statementDescriptor;
    private String paymentMethod;
    private CreditCardContract creditCard;
}
