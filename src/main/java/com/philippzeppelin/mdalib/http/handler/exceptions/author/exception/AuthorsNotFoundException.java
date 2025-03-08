package com.philippzeppelin.mdalib.http.handler.exceptions.author.exception;

public class AuthorsNotFoundException extends RuntimeException {
    public AuthorsNotFoundException(String message) {
        super(message);
    }
}
