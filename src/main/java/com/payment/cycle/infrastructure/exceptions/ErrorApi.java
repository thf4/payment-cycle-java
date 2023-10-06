package com.payment.cycle.infrastructure.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude
public class ErrorApi implements Serializable {

    private static final long serialVersionUUID = 1L;
    public ErrorApi(int status, String path, String message) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    private final int status;
    private final String path;
    private final String message;
    private final String timestamp;
    private List<Fields> fields;

    @Getter
    @Builder
    public static class Fields {
        private String name;
        private String message;
    }
}
