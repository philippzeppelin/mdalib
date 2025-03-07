package com.philippzeppelin.mdalib.http.handler.exceptions.author.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
