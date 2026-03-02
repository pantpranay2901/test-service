package com.company.servicename.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

        // --- 400: Validation (Bean Validation @Valid) ---
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidationException(
                        MethodArgumentNotValidException ex,
                        HttpServletRequest request) {

                log.error("Validation failed for request [{}]: {}", request.getRequestURI(), ex.getMessage());

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .status(HttpStatus.BAD_REQUEST.value())
                                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                .message("Validation failed")
                                .path(request.getRequestURI())
                                .build();

                return ResponseEntity.badRequest().body(errorResponse);
        }

        // --- 400: Bad Request (business logic) ---
        @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<ErrorResponse> handleBadRequestException(
                        BadRequestException ex,
                        HttpServletRequest request) {

                log.error("Bad request for [{}]: {}", request.getRequestURI(), ex.getMessage());

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .status(HttpStatus.BAD_REQUEST.value())
                                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                .message(ex.getMessage())
                                .path(request.getRequestURI())
                                .build();

                return ResponseEntity.badRequest().body(errorResponse);
        }

        // --- 404: Resource Not Found ---
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
                        ResourceNotFoundException ex,
                        HttpServletRequest request) {

                log.error("Resource not found for [{}]: {}", request.getRequestURI(), ex.getMessage());

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .message(ex.getMessage())
                                .path(request.getRequestURI())
                                .build();

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        // --- 409: Conflict ---
        @ExceptionHandler(ConflictException.class)
        public ResponseEntity<ErrorResponse> handleConflictException(
                        ConflictException ex,
                        HttpServletRequest request) {

                log.error("Conflict for [{}]: {}", request.getRequestURI(), ex.getMessage());

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .status(HttpStatus.CONFLICT.value())
                                .error(HttpStatus.CONFLICT.getReasonPhrase())
                                .message(ex.getMessage())
                                .path(request.getRequestURI())
                                .build();

                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        // --- 500: Generic fallback ---
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGenericException(
                        Exception ex,
                        HttpServletRequest request) {

                log.error("Unhandled exception for request [{}]: {}", request.getRequestURI(), ex.getMessage(), ex);

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                .message("An unexpected error occurred")
                                .path(request.getRequestURI())
                                .build();

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
}
