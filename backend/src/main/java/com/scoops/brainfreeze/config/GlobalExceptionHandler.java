package com.scoops.brainfreeze.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Intentionally weak exception handling for educational purposes (A10).
 * In a real application we would return generic messages and log the details server-side.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", true);
        body.put("message", ex.getMessage());
        body.put("exception", ex.getClass().getName());
        // Intentionally including stack trace for A10 demonstration
        body.put("stackTrace", ex.getStackTrace());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
