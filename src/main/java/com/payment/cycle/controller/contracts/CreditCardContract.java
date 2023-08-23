package com.payment.cycle.controller.contracts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreditCardContract {
    private String number;
    private String  holderName;
    private short expMonth;
    private short expYear;
    private String cvv;
    private AddressContract address;
}
