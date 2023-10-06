package com.payment.cycle.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class CustomerDto {
    @NotEmpty
    private String name;
    @Email
    private String email;
    @NotEmpty
    private String lastName;
    @CPF
    private String cpf;
}
