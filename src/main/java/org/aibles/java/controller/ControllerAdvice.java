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
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
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
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getReason());

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("path", "/api/v1/users/register");
        responseBody.put("timestamps", Instant.now().getEpochSecond());
        responseBody.put("error", errorDetails);

        return new ResponseEntity<>(responseBody, ex.getStatusCode());
    }
}
