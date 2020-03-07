package com.hgh.plangenerator.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("Exception happened: {}", ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({InputsNotValidException.class})
    public ResponseEntity<Object> handleApiException(final InputsNotValidException ex, final WebRequest request) {
        logger.error("Inputs Not Valid Exception happened: {}", ex);
        final ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }
}
