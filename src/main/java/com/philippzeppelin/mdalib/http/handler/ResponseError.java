package com.philippzeppelin.mdalib.http.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseError {

    private final String error;
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime time = LocalDateTime.now();

    public ResponseError(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
