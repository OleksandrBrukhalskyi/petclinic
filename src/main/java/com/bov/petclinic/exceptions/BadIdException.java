package com.bov.petclinic.exceptions;

public class BadIdException extends RuntimeException {
    public BadIdException(String message) {
        super(message);
    }
}
