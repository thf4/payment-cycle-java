package com.payment.cycle.dto;

import com.payment.cycle.domain.Customer;
import com.payment.cycle.domain.Resource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
    private Customer customer;
    private double amount;
    private String description;
    private Resource resource;
}
