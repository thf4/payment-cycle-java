package com.payment.cycle.infrastructure.service.contracts;

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
