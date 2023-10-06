package com.payment.cycle.infrastructure.service;

import com.payment.cycle.domain.model.PaymentMessage;
import com.payment.cycle.domain.entity.PaymentEntity;
import com.payment.cycle.domain.model.PaymentModel;
import com.payment.cycle.infrastructure.exceptions.GenericException;
import com.payment.cycle.infrastructure.repository.*;
import com.payment.cycle.infrastructure.service.contracts.*;
import com.payment.cycle.infrastructure.service.interfaces.IPaymentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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
    CardRepository cardRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmailService emailService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public void save(PaymentModel payment) throws GenericException {
        PaymentEntity paymentMapper = modelMapper.map(payment, PaymentEntity.class);
        paymentMapper.setRegistrationDate(LocalDateTime.now());
        paymentMapper.setUpdateDate(LocalDateTime.now());
        paymentMapper.setPaymentStatus("payment-register-created");

        cardRepository.save(paymentMapper.getResource().getCreditCard());

        customerRepository.save(paymentMapper.getCustomer());

        paymentRepository.save(paymentMapper);

        emailService.sendMail("Welcome To The Jungle");
    }

    public List<PaymentEntity> findAll() {
        return paymentRepository.findAll();
    }

    public void updatePayment(PaymentMessage paymentMessage) throws GenericException {
        var payment = paymentRepository.findById(UUID.fromString(paymentMessage.getPayment_id()))
                .orElseThrow(() -> new GenericException("Payment has not be found!"));;
        payment.setPaymentStatus(paymentMessage.getPayment_status());
        paymentRepository.save(payment);
    }

    public PaymentContractResponse findById(UUID id) throws GenericException {
        var payment = paymentRepository.findById(id).orElseThrow(() -> new GenericException("Payment has not be found!"));
        var customer = customerRepository.findById(payment.getCustomer().getId()).orElseThrow(() -> new GenericException("Customer has not be found!"));;
        var card = cardRepository.findById(payment.getResource().getCreditCard().getId()).orElseThrow(() -> new GenericException("CreditCard has not be found!"));;
        var address = card.getAddress();
        var resource = payment.getResource();

        var b = AddressContract.builder()
                .city(address.getCity())
                .state(address.getState())
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();

        var c = CustomerContract.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .build();

        var car = CreditCardContract.builder()
                .address(b)
                .cvv(card.getCvv())
                .expMonth(card.getExpMonth())
                .expYear(card.getExpYear())
                .holderName(card.getHolderName())
                .number(card.getNumber())
                .build();

        var r = ResourceContract.builder()
                .statementDescriptor(resource.getStatementDescriptor())
                .paymentMethod(resource.getPaymentMethod())
                .installments(resource.getInstallments())
                .recurrence(resource.getRecurrence())
                .creditCard(car)
                .build();

        var builder = PaymentContractResponse.builder();
        builder
                .amount(payment.getAmount())
                .description(payment.getDescription())
                .paymentStatus(payment.getPaymentStatus())
                .customer(c)
                .resource(r)
                .build();

        return builder.build();
    }
}
