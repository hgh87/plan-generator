package com.hgh.plangenerator.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private LocalDateTime localDateTime;
    private String message;
    private String details;

    public ExceptionResponse(LocalDateTime localDateTime, String message, String details) {
        super();
        this.localDateTime = localDateTime;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
