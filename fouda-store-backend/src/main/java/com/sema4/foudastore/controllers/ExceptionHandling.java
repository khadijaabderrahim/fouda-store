package com.sema4.foudastore.controllers;

import com.sema4.foudastore.dto.ErrorResponse;
import com.sema4.foudastore.exceptions.BadDataEntryException;
import com.sema4.foudastore.exceptions.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class ExceptionHandling {

    @ExceptionHandler({ ElementNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleNotFoundException(ElementNotFoundException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(null,ex.getMessage()));
    }


    @ExceptionHandler(BadDataEntryException.class )
    public ResponseEntity<ErrorResponse> handleBadDataEntryException(BadDataEntryException ex) {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(null,ex.getMessage()));
    }

}
