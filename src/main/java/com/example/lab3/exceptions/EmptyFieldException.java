package com.example.lab3.exceptions;

import org.springframework.http.HttpStatus;

public class EmptyFieldException extends ApiException {
    public EmptyFieldException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
