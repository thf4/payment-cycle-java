package com.payment.cycle.controller.contracts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CustomerContract {
    private String name;
    private String email;
}
