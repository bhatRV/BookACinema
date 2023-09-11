package com.rv.booking.ticket.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;


@ControllerAdvice
public class GlobalExceptionHandler {
    /** Provides handling for exceptions throughout this service. */
    @ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class })
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof IllegalArgumentException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            IllegalArgumentException unfe = (IllegalArgumentException) ex;
            return handleBadRequest(unfe, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            IllegalStateException cnae = (IllegalStateException) ex;
            return handleExceptionInternal(cnae, new ApiError(Collections.singletonList(ex.getMessage())), headers, status, request);

        }
    }

     protected ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, new ApiError(Collections.singletonList(ex.getMessage())), headers, status, request);
    }



     protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return new ResponseEntity<>(body, headers, status);
    }
}