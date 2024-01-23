package com.sema4.foudastore.exceptions;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException() {
    }

    public ElementNotFoundException(String message) {
        super(message);
    }
}
