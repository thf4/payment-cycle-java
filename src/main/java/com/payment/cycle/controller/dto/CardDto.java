package com.payment.cycle.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CardDto {
    @NotBlank
    public String number;
    @NotBlank
    public String  holderName;
    @NotBlank
    public Short expMonth;
    @NotBlank
    public Short expYear;
    @NotBlank
    public String cvv;
    @NotNull
    public AddressDto address;
}
