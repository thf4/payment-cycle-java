package com.payment.cycle.infrastructure.service.interfaces;

import com.payment.cycle.controller.contracts.PaymentContractResponse;
import com.payment.cycle.dto.PaymentDto;
import com.payment.cycle.entity.PaymentEntity;

import java.util.List;
import java.util.UUID;

public interface IPaymentService {
    public PaymentContractResponse findById(UUID id);
    public List<PaymentEntity> findAll();
    public PaymentEntity save(PaymentDto payment);
}
