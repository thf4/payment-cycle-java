package com.payment.cycle.infrastructure.exceptions;

public class ResponseNotFoundException extends RuntimeException {
    public ResponseNotFoundException(String message) {
        super(message);
    }
}
