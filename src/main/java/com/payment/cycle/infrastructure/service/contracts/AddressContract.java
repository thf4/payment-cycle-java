package com.payment.cycle.infrastructure.service.contracts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class AddressContract {
    private String street;
    private String zipCode;
    private String city;
    private String state;
    private String country;
    private String houseNumber;
}
