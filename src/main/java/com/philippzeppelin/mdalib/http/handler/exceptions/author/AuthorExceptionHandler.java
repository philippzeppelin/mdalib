package com.philippzeppelin.mdalib.http.handler.exceptions.author;

import com.philippzeppelin.mdalib.http.handler.ResponseError;
import com.philippzeppelin.mdalib.http.handler.exceptions.author.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthorExceptionHandler {

    @ExceptionHandler(InvalidAuthorException.class)
    public ResponseEntity<ResponseError> handleInvalidAuthorException(InvalidAuthorException e) {
        log.error("Validation error: {}", e.getMessage());
        ResponseError error = new ResponseError("Validation error", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AuthorPersistenceException.class)
    public ResponseEntity<ResponseError> handleAuthorPersistenceException(AuthorPersistenceException e) {
        log.error("Persistence error: {}", e.getMessage());
        ResponseError error = new ResponseError("Persistence error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(AuthorBooksNotFoundException.class)
    public ResponseEntity<ResponseError> handleAuthorBooksNotFoundException(AuthorBooksNotFoundException e) {
        log.error("Books not found: {}", e.getMessage());
        ResponseError error = new ResponseError("Books not found", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ResponseError> handleAuthorNotFoundException(AuthorNotFoundException e) {
        log.error("Author not found: {}", e.getMessage());
        ResponseError error = new ResponseError("Author not found", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AuthorsNotFoundException.class)
    public ResponseEntity<ResponseError> handleAuthorsNotFoundException(AuthorsNotFoundException e) {
        log.error("Authors not found: {}", e.getMessage());
        ResponseError error = new ResponseError("Authors not found", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
