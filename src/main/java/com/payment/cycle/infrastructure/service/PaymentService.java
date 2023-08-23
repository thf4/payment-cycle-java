package com.payment.cycle.infrastructure.service;

import com.payment.cycle.controller.contracts.*;
import com.payment.cycle.dto.PaymentDto;
import com.payment.cycle.entity.*;
import com.payment.cycle.infrastructure.repository.*;
import com.payment.cycle.infrastructure.service.interfaces.IPaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public PaymentEntity save(PaymentDto payment) {
        PaymentEntity paymentEntity = new PaymentEntity();
        CustomerEntity customerEntity = new CustomerEntity();
        AddressEntity addressEntity = new AddressEntity();
        CardEntity cardEntity = new CardEntity();
        ResourceEntity resourceEntity = new ResourceEntity();

        BeanUtils.copyProperties(payment, paymentEntity);
        paymentEntity.setRegistrationDate(LocalDateTime.now());
        paymentEntity.setUpdateDate(LocalDateTime.now());
        paymentEntity.setPaymentStatus("register-payment-created");

        BeanUtils.copyProperties(payment.getCustomer(), customerEntity);
        CustomerEntity customer = customerRepository.save(customerEntity);

        BeanUtils.copyProperties(payment.getResource().getCreditCard().getAddress(), addressEntity);
        addressEntity.setCustomer(customer.getId());
        AddressEntity address = addressRepository.save(addressEntity);

        cardEntity.setAddress(address.getId());
        cardEntity.setCustomer(customer.getId());
        BeanUtils.copyProperties(payment.getResource().getCreditCard(), cardEntity);
        CardEntity card = cardRepository.save(cardEntity);


        resourceEntity.setCreditCard(card.getId());
        BeanUtils.copyProperties(payment.getResource(), resourceEntity);
        ResourceEntity resource = resourceRepository.save(resourceEntity);

        paymentEntity.setCustomer(customer.getId());
        paymentEntity.setResource(resource.getId());

        return paymentRepository.save(paymentEntity);
    }

    public List<PaymentEntity> findAll() {
        return paymentRepository.findAll();
    }

    public PaymentContractResponse findById(UUID id) {
        var payment = paymentRepository.findById(id);
        var customer = customerRepository.findById(payment.get().getCustomer());
        var resource = resourceRepository.findById(payment.get().getResource());
        var card = cardRepository.findById(resource.get().getCreditCard());
        var address = addressRepository.findById(card.get().getAddress());

        var b = AddressContract.builder()
                .city(address.get().getCity())
                .state(address.get().getState())
                .houseNumber(address.get().getHouseNumber())
                .street(address.get().getStreet())
                .country(address.get().getCountry())
                .zipCode(address.get().getZipCode())
                .build();

        var c = CustomerContract.builder()
                .name(customer.get().getName())
                .email(customer.get().getEmail())
                .build();

        var car = CreditCardContract.builder()
                .address(b)
                .cvv(card.get().getCvv())
                .expMonth(card.get().getExpMonth())
                .expYear(card.get().getExpYear())
                .holderName(card.get().getHolderName())
                .number(card.get().getNumber())
                .build();

        var r = ResourceContract.builder()
                .statementDescriptor(resource.get().getStatementDescriptor())
                .paymentMethod(resource.get().getPaymentMethod())
                .installments(resource.get().getInstallments())
                .recurrence(resource.get().isRecurrence())
                .creditCard(car)
                .build();

        var builder = PaymentContractResponse.builder();
        builder
                .amount(payment.get().getAmount())
                .description(payment.get().getDescription())
                .paymentStatus(payment.get().getPaymentStatus())
                .customer(c)
                .resource(r)
                .build();

        return builder.build();
    }
}
