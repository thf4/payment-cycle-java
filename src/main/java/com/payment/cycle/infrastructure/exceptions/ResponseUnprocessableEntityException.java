package com.payment.cycle.infrastructure.exceptions;

public class ResponseUnprocessableEntityException extends RuntimeException {
    public ResponseUnprocessableEntityException(String message) {
        super(message);
    }
}
