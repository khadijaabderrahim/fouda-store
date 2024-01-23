package com.sema4.foudastore.controllers;

import com.sema4.foudastore.exceptions.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class ExceptionHandling {

    @ExceptionHandler({ ElementNotFoundException.class })
    public ResponseEntity handleNotFoundException(ElementNotFoundException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }
}
