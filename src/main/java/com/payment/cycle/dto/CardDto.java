package com.payment.cycle.dto;

import com.payment.cycle.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
    public String number;
    public String  holderName;
    public short expMonth;
    public short expYear;
    public String cvv;
    public Address address;
}
