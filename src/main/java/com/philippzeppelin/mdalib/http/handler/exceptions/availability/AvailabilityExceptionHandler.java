package com.philippzeppelin.mdalib.http.handler.exceptions.availability;

import com.philippzeppelin.mdalib.http.handler.ResponseError;
import com.philippzeppelin.mdalib.http.handler.exceptions.availability.exception.AvailabilitiesNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AvailabilityExceptionHandler {

    @ExceptionHandler(AvailabilitiesNotFoundException.class)
    public ResponseEntity<Object> handle(AvailabilitiesNotFoundException e) {
        log.error("Availabilities not found", e);
        ResponseError responseError = new ResponseError("Availabilities not found", e.getMessage());
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }
}
