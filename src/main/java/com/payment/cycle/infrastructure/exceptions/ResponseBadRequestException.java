package com.payment.cycle.infrastructure.exceptions;

public class ResponseBadRequestException extends RuntimeException {
    public ResponseBadRequestException(String message) {
        super(message);
    }
}
