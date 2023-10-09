package com.payment.cycle.config.kafka;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.cycle.domain.model.PaymentMessage;
import com.payment.cycle.infrastructure.exceptions.GenericException;
import com.payment.cycle.infrastructure.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerConfig {
    @Autowired
    private PaymentService paymentService;
    @KafkaListener(topics = "tp_payment-cycle")
    public void consumer(PaymentMessage message) throws GenericException {

        paymentService.updatePayment(message);
    }
}
