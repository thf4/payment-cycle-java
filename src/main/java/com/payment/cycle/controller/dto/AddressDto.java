package com.payment.cycle.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDto {
    @NotBlank
    public String street;
    @NotBlank
    public String zipCode;
    @NotBlank
    public String city;
    @NotBlank
    public String state;
    @NotBlank
    public String country;
    @NotBlank
    public String houseNumber;
}
