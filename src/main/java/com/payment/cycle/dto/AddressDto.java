package com.payment.cycle.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
    public String street;
    public String zipCode;
    public String city;
    public String state;
    public String country;
    public String houseNumber;
}
