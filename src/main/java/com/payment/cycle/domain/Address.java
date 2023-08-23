package com.payment.cycle.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address{
    public String street;
    public String zipCode;
    public String city;
    public String state;
    public String country;
    public String houseNumber;
}
