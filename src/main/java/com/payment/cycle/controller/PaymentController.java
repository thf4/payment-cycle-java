package com.payment.cycle.controller;

import com.payment.cycle.domain.model.PaymentModel;
import com.payment.cycle.infrastructure.service.contracts.PaymentContractResponse;
import com.payment.cycle.domain.model.PaymentMessage;
import com.payment.cycle.controller.dto.PaymentDto;
import com.payment.cycle.domain.entity.PaymentEntity;
import com.payment.cycle.infrastructure.exceptions.GenericException;
import com.payment.cycle.infrastructure.exceptions.ResponseNotFoundException;
import com.payment.cycle.infrastructure.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentEntity> list() throws RuntimeException {
        return paymentService.findAll();
    }

    @PostMapping(value = "create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPayment(@RequestBody @Valid PaymentDto payment) throws RuntimeException, GenericException {

        paymentService.save(modelMapper.map(payment, PaymentModel.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) throws RuntimeException, GenericException {
        Optional<PaymentContractResponse> payment = Optional.ofNullable(paymentService.findById(id));
        if(payment.isEmpty()) throw new ResponseNotFoundException("Payment not found");
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }

    @PostMapping("/kafka")
    public ResponseEntity<?> sendKafkaMessage(@RequestBody PaymentMessage paymentMessage) throws IOException {
        try {
            kafkaTemplate.send("tp_payment-cycle", paymentMessage);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
