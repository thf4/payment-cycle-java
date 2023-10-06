package com.payment.cycle.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@JsonInclude
public class PaymentMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public String payment_status;
    public String payment_id;
}
