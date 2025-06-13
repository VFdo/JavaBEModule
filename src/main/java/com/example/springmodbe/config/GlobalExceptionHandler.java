package com.example.springmodbe.config;

import com.example.springmodbe.dto.response.ApiErrorResponse;
import com.example.springmodbe.exception.AlreadyExistsException;
import com.example.springmodbe.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleUserAlreadyExistsException(AlreadyExistsException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), "Resource Already Exists");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Resource Not Found");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "Bad Request");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGlobalException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", ex.getMessage());
    }
    private ResponseEntity<ApiErrorResponse> buildResponse(HttpStatus status, String message, String error) {
        ApiErrorResponse response = new ApiErrorResponse(
                status.value(),
                message,
                error
        );
        return ResponseEntity.status(status).body(response);
    }
}
