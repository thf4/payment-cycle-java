package com.payment.cycle.domain;

import com.payment.cycle.dto.AddressDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    public String number;
    public String  holderName;
    public short expMonth;
    public short expYear;
    public String cvv;
    public AddressDto address;
}