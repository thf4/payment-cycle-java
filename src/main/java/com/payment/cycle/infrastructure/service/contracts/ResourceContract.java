package com.payment.cycle.infrastructure.service.contracts;

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
