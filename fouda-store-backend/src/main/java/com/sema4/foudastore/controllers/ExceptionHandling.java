package com.sema4.foudastore.controllers;

import com.sema4.foudastore.dto.ErrorResponse;
import com.sema4.foudastore.exceptions.BadDataEntryException;
import com.sema4.foudastore.exceptions.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public abstract class ExceptionHandling {

    @ExceptionHandler({ ElementNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleNotFoundException(ElementNotFoundException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(null,ex.getMessage()));
    }


    @ExceptionHandler(BadDataEntryException.class )
    public ResponseEntity<ErrorResponse> handleBadDataEntryException(BadDataEntryException ex) {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(null,ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
