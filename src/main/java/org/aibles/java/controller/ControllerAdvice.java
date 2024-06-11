package org.aibles.java.controller;

import org.aibles.java.dto.response.ErrorDetail;
import org.aibles.java.dto.response.ErrorResponse;
import org.aibles.java.exception.InvalidRequestException;
import org.aibles.java.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException exception) {
        ErrorDetail errorDetail = new ErrorDetail(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("invalid_otp", System.currentTimeMillis(), errorDetail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}