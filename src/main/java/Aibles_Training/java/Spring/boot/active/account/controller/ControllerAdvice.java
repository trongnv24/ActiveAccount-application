package Aibles_Training.java.Spring.boot.active.account.controller;

import Aibles_Training.java.Spring.boot.active.account.dto.response.ErrorDetail;
import Aibles_Training.java.Spring.boot.active.account.dto.response.ErrorResponse;
import Aibles_Training.java.Spring.boot.active.account.exception.InvalidRequestException;
import Aibles_Training.java.Spring.boot.active.account.exception.NotFoundException;
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