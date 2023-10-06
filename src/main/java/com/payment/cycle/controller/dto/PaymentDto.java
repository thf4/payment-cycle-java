package com.payment.cycle.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {
    @NotNull
    private CustomerDto customer;
    @NotNull
    private BigDecimal amount;
    @NotBlank
    private String description;
    @NotNull
    private ResourceDto resource;
}
