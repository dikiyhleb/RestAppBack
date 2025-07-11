package dev.barapp.controllers;

import dev.barapp.exceptions.UserAlreadyExistsException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        ErrorResponse err = ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Not Found!")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getBody());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ErrorResponse err = ErrorResponse.builder(ex, HttpStatus.CONFLICT, ex.getMessage())
                .title("Email Already Exists!")
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err.getBody());
    }
}
