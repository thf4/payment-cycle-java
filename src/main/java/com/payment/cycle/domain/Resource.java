package com.payment.cycle.domain;

import com.payment.cycle.dto.CardDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource {
    private boolean recurrence;
    private short installments;
    private String statementDescriptor;
    private String paymentMethod;
    private CardDto creditCard;
}

