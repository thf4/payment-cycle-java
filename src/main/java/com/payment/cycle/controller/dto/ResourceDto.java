package com.payment.cycle.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResourceDto {
    @NotNull
    private Boolean recurrence;
    @NotBlank
    private Short installments;
    @NotBlank
    private String statementDescriptor;
    @NotBlank
    private String paymentMethod;
    @NotNull
    private CardDto creditCard;
}
