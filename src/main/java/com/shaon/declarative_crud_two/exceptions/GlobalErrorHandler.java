package com.shaon.declarative_crud_two.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    // You can define global exception handling methods here
    // For example, you can handle specific exceptions and return custom responses

    // Example:
     @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
     }

    // You can also handle other exceptions like ValidationException, etc.
    // Add more exception handling methods as needed
    // Example:
     @ExceptionHandler(ValidationException.class)
     public ResponseEntity<String> handleValidationException(ValidationException ex) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
     }

}
