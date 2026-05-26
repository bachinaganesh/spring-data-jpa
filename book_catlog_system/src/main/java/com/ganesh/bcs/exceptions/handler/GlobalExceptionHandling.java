package com.ganesh.bcs.exceptions.handler;

import com.ganesh.bcs.dtos.response.APIError;
import com.ganesh.bcs.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request){
        APIError apiError = APIError.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .status(org.springframework.http.HttpStatus.NOT_FOUND)
                .timestamp(java.time.LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleException(Exception e, HttpServletRequest request){
        APIError apiError = APIError.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(java.time.LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
