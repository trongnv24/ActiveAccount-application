package Aibles_Training.java.Spring.boot.active.account.controller;

import Aibles_Training.java.Spring.boot.active.account.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static io.lettuce.core.pubsub.PubSubOutput.Type.message;

@RestControllerAdvice
public class ControllerAdvance {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
