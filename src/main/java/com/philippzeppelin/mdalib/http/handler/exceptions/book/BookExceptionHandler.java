package com.philippzeppelin.mdalib.http.handler.exceptions.book;

import com.philippzeppelin.mdalib.http.handler.ResponseError;
import com.philippzeppelin.mdalib.http.handler.exceptions.book.exception.BookDeletionException;
import com.philippzeppelin.mdalib.http.handler.exceptions.book.exception.BookNotFoundException;
import com.philippzeppelin.mdalib.http.handler.exceptions.book.exception.BookPersistenceException;
import com.philippzeppelin.mdalib.http.handler.exceptions.book.exception.InvalidBookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(InvalidBookException.class)
    public ResponseEntity<ResponseError> handleInvalidBookException(InvalidBookException e) {
        log.error("Validation error: {}", e.getMessage());
        ResponseError error = new ResponseError("Validation error", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BookPersistenceException.class)
    public ResponseEntity<ResponseError> handleBookPersistenceException(BookPersistenceException e) {
        log.error("Persistence error: {}", e.getMessage());
        ResponseError error = new ResponseError("Persistence error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ResponseError> handleBookNotFoundException(BookNotFoundException e) {
        log.error("Book not found: {}", e.getMessage());
        ResponseError error = new ResponseError("Book not found", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BookDeletionException.class)
    public ResponseEntity<ResponseError> handleBookDeletionException(BookDeletionException e) {
        log.error("Book deletion error: {}", e.getMessage());
        ResponseError error = new ResponseError("Book deletion error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
