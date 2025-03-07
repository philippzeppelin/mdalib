package com.philippzeppelin.mdalib.http.handler.exceptions.author;

public class AuthorBooksNotFoundException extends RuntimeException {
    public AuthorBooksNotFoundException(String message) {
        super(message);
    }
}
