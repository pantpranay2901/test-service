package com.company.servicename.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    @SuppressWarnings("unchecked")
    public static <T> ApiResponse<T> success(String message) {
        return (ApiResponse<T>) ApiResponse.builder()
                .success(true)
                .message(message)
                .build();
    }

    @SuppressWarnings("unchecked")
    public static <T> ApiResponse<T> error(String message) {
        return (ApiResponse<T>) ApiResponse.builder()
                .success(false)
                .message(message)
                .build();
    }
}
