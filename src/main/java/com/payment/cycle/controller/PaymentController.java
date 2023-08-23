package com.payment.cycle.controller;

import com.payment.cycle.controller.contracts.*;
import com.payment.cycle.entity.*;
import com.payment.cycle.dto.PaymentDto;
import com.payment.cycle.infrastructure.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() throws RuntimeException {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findAll());
    }

    @PostMapping(value = "create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPayment( @Valid @RequestBody PaymentDto payment) throws RuntimeException {

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(payment));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) throws RuntimeException {

        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findById(id));
    }

}
