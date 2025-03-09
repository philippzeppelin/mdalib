package com.philippzeppelin.mdalib.http.handler.exceptions.book.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
