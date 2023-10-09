package com.payment.cycle.infrastructure.service.interfaces;

import com.payment.cycle.domain.model.PaymentModel;
import com.payment.cycle.infrastructure.service.contracts.PaymentContractResponse;
import com.payment.cycle.domain.model.PaymentMessage;
import com.payment.cycle.domain.entity.PaymentEntity;
import com.payment.cycle.infrastructure.exceptions.GenericException;

import java.util.List;
import java.util.UUID;

public interface IPaymentService {
    PaymentEntity findById(UUID id) throws GenericException;
    List<PaymentEntity> findAll();
    void save(PaymentModel payment) throws GenericException;

    void updatePayment(PaymentMessage id) throws GenericException;


}
