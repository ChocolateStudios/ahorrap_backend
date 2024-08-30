package com.chocolatestudios.ahorrapp._middleware;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chocolatestudios.ahorrapp.contexts._shared.resources.ErrorResource;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // Runtime Exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResource> handleException(Exception ex) {
        ErrorResource resource = new ErrorResource();
        resource.setMessage(ex.getMessage());

        HttpStatus status = 
            ex.getClass().getAnnotation(ResponseStatus.class) != null
                ? ex.getClass().getAnnotation(ResponseStatus.class).value()
                : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(resource);
    }

    // Validation Exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
