package com.payment.cycle.dto;

import com.payment.cycle.domain.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceDto {
    private boolean recurrence;
    private short installments;
    private String statementDescriptor;
    private String paymentMethod;
    private Card creditCard;
}
