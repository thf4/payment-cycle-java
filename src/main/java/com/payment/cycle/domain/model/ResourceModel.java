package com.payment.cycle.domain.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceModel {
    private Boolean recurrence;
    private Short installments;
    private String statementDescriptor;
    private String paymentMethod;
    private CardModel creditCard;
}

