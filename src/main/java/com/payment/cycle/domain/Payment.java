package com.payment.cycle.domain;

import com.payment.cycle.dto.CustomerDto;
import com.payment.cycle.dto.ResourceDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    private CustomerDto customer;
    private double amount;
    private String description;
    private ResourceDto resource;
}

