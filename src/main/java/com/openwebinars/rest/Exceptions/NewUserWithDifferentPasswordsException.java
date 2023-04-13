package com.openwebinars.rest.Exceptions;

public class NewUserWithDifferentPasswordsException extends RuntimeException {

    private static final long serialVersionUID = 23433132321432321L;

    public NewUserWithDifferentPasswordsException() {
        super("Las contraseñas no coinciden");
    }
}