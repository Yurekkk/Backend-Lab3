package com.example.lab3.exceptions;

import com.example.lab3.DTOs.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(ApiException exception) {

        var error = new ErrorResponseDTO(exception.getMessage());

        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(error);
    }

}