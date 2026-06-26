package com.arunn.companyservice.exception;

import com.arunn.companyservice.VO.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CompanyExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleException(HttpServletRequest request, ResourceNotFoundException exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .timestamps(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleException(HttpServletRequest request, ResourceAlreadyExistsException exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .timestamps(LocalDateTime.now())
                .status(HttpStatus.CONFLICT)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(HttpServletRequest request, Exception exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .timestamps(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
