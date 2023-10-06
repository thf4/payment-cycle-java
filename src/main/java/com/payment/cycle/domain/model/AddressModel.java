package com.payment.cycle.domain.model;

import lombok.Data;
@Data
public class AddressModel{
    public String street;
    public String zipCode;
    public String city;
    public String state;
    public String country;
    public String houseNumber;
}
