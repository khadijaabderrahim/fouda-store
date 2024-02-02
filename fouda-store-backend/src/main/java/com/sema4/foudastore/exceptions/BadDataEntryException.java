package com.sema4.foudastore.exceptions;

public class BadDataEntryException extends RuntimeException{


    public BadDataEntryException() {
    }

    public BadDataEntryException(String message) {
        super(message);
    }
}
