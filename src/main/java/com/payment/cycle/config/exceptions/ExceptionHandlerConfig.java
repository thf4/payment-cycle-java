package com.payment.cycle.config.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.cycle.infrastructure.exceptions.ErrorApi;
import com.payment.cycle.infrastructure.exceptions.ResponseBadRequestException;
import com.payment.cycle.infrastructure.exceptions.ResponseNotFoundException;
import com.payment.cycle.infrastructure.exceptions.ResponseUnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import org.hibernate.PropertyValueException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler  {
    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatusCode status,
            @NotNull WebRequest request) {

        ErrorApi errorApi = new ErrorApi(HttpStatus.UNPROCESSABLE_ENTITY.value(), "", "Alguns campos estão invalidos!!");
        List<ErrorApi.Fields> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                    return ErrorApi.Fields.builder()
                        .name(fieldError.getField())
                        .message(message)
                        .build();
                })
                .toList();
        errorApi.setFields(errors);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(errorApi);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleUncaught(HttpServletRequest request, Exception e) throws RuntimeException {
        ErrorApi errorApi = new ErrorApi(HttpStatus.BAD_REQUEST.value(), request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorApi);
    }
    @ExceptionHandler({ ResponseBadRequestException.class })
    public ResponseEntity<Object> handleBadRequest(HttpServletRequest request, ResponseBadRequestException e) throws RuntimeException {
        ErrorApi errorApi = new ErrorApi(HttpStatus.BAD_REQUEST.value(), request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorApi);
    }

    @ExceptionHandler({ ResponseNotFoundException.class })
    public ResponseEntity<Object> handleNotFound(HttpServletRequest request, ResponseNotFoundException e) throws RuntimeException {
        ErrorApi errorApi = new ErrorApi(HttpStatus.NOT_FOUND.value(), request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorApi);
    }
}
