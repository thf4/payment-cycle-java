package com.payment.cycle.domain.model;

import com.payment.cycle.domain.views.PaymentView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerModel {
    private String name;
    private String email;
    private String lastName;
    private String cpf;
}
