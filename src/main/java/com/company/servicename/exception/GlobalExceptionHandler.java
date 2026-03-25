package com.company.servicename.exception;

import com.company.servicename.dto.response.ApiError;
import com.company.servicename.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

        // --- 400: Validation (Bean Validation @Valid) ---
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Void>> handleValidationException(
                        MethodArgumentNotValidException ex,
                        HttpServletRequest request) {

                log.error("Validation failed for request [{}]: {}", request.getRequestURI(), ex.getMessage());

                Map<String, String> validationErrors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .collect(Collectors.toMap(
                                                fieldError -> fieldError.getField(),
                                                fieldError -> fieldError.getDefaultMessage() != null
                                                                ? fieldError.getDefaultMessage()
                                                                : "Invalid value",
                                                (existing, replacement) -> existing));

                ApiResponse<Void> errorResponse = ApiResponse.error(
                                "Validation failed",
                                ApiError.builder()
                                                .status(HttpStatus.BAD_REQUEST.value())
                                                .code("VALIDATION_ERROR")
                                                .details(validationErrors)
                                                .build());

                return ResponseEntity.badRequest().body(errorResponse);
        }

        // --- 400: Bad Request (business logic) ---
        @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<ApiResponse<Void>> handleBadRequestException(
                        BadRequestException ex,
                        HttpServletRequest request) {

                log.error("Bad request for [{}]: {}", request.getRequestURI(), ex.getMessage());

                ApiResponse<Void> errorResponse = ApiResponse.error(
                                ex.getMessage(),
                                ApiError.builder()
                                                .status(HttpStatus.BAD_REQUEST.value())
                                                .code("BAD_REQUEST")
                                                .build());

                return ResponseEntity.badRequest().body(errorResponse);
        }

        // --- 404: Resource Not Found ---
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(
                        ResourceNotFoundException ex,
                        HttpServletRequest request) {

                log.error("Resource not found for [{}]: {}", request.getRequestURI(), ex.getMessage());

                ApiResponse<Void> errorResponse = ApiResponse.error(
                                ex.getMessage(),
                                ApiError.builder()
                                                .status(HttpStatus.NOT_FOUND.value())
                                                .code("RESOURCE_NOT_FOUND")
                                                .build());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        // --- 409: Conflict ---
        @ExceptionHandler(ConflictException.class)
        public ResponseEntity<ApiResponse<Void>> handleConflictException(
                        ConflictException ex,
                        HttpServletRequest request) {

                log.error("Conflict for [{}]: {}", request.getRequestURI(), ex.getMessage());

                ApiResponse<Void> errorResponse = ApiResponse.error(
                                ex.getMessage(),
                                ApiError.builder()
                                                .status(HttpStatus.CONFLICT.value())
                                                .code("CONFLICT")
                                                .build());

                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        // --- 500: Generic fallback ---
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<Void>> handleGenericException(
                        Exception ex,
                        HttpServletRequest request) {

                log.error("Unhandled exception for request [{}]: {}", request.getRequestURI(), ex.getMessage(), ex);

                ApiResponse<Void> errorResponse = ApiResponse.error(
                                "An unexpected error occurred",
                                ApiError.builder()
                                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                                .code("INTERNAL_SERVER_ERROR")
                                                .build());

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
}
