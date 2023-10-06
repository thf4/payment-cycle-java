package com.payment.cycle.domain.model;

import com.payment.cycle.domain.model.AddressModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardModel {
    public String number;
    public String  holderName;
    public Short expMonth;
    public Short expYear;
    public String cvv;
    public AddressModel address;
}