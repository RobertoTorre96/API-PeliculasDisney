package com.Disney.DisneyApp.exceptions.customException;

public class EntityExistException extends RuntimeException {
    public EntityExistException(String message) {
        super(message);
    }
}
