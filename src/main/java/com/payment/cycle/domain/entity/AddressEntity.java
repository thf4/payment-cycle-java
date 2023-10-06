package com.payment.cycle.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AddressEntity {
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String houseNumber;
}
