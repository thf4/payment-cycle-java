package com.payment.cycle.infrastructure.service;

import com.payment.cycle.domain.entity.CardEntity;
import com.payment.cycle.domain.entity.CustomerEntity;
import com.payment.cycle.domain.entity.ResourceEntity;
import com.payment.cycle.domain.model.PaymentMessage;
import com.payment.cycle.domain.entity.PaymentEntity;
import com.payment.cycle.domain.model.PaymentModel;
import com.payment.cycle.infrastructure.exceptions.GenericException;
import com.payment.cycle.infrastructure.repository.*;
import com.payment.cycle.infrastructure.service.interfaces.IPaymentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    CardService cardService;
    @Autowired
    CustomerService customerService ;

    @Autowired
    EmailService emailService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public void save(PaymentModel payment) throws GenericException {
        PaymentEntity paymentMapper = modelMapper.map(payment, PaymentEntity.class);
        paymentMapper.setRegistrationDate(LocalDateTime.now());
        paymentMapper.setUpdateDate(LocalDateTime.now());
        paymentMapper.setPaymentStatus("payment-register-created");

        this.saveCustomer(paymentMapper);
        cardService.save(paymentMapper.getResource().getCreditCard());

        paymentRepository.save(paymentMapper);

        //emailService.sendMail(paymentMapper.getCustomer().getEmail());
    }

    private void saveCustomerCard(PaymentEntity paymentMapper) {
        Optional<CardEntity> card = cardService.findCardByNumber(paymentMapper.getResource().getCreditCard().getNumber());
        if (card.isEmpty()) {
            cardService.save(paymentMapper.getResource().getCreditCard());
        } else {
            paymentMapper.getResource().setCreditCard(card.get());
        }
    }

    private void saveCustomer(PaymentEntity paymentMapper) {
        Optional<CustomerEntity> customer = customerService.findCustomerByCpf(paymentMapper.getCustomer().getCpf());
        if (customer.isEmpty()) {
            customerService.save(paymentMapper.getCustomer());
        } else {
            paymentMapper.setCustomer(customer.get());
        }
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

    public PaymentEntity findById(UUID id) throws GenericException {
        return paymentRepository.findById(id).orElseThrow(() -> new GenericException("Payment has not be found!"));
    }
}
