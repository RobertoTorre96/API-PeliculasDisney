package com.Disney.DisneyApp.exceptions.customException;

public class EntityNotFoundException extends  RuntimeException{
    public EntityNotFoundException(String msj) {
        super(msj);
    }
}
