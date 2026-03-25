package com.company.servicename.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "ApiResponse", description = "Standard API response envelope for both success and error cases")
public class ApiResponse<T> {

    @Schema(description = "Whether the request was successful", example = "true")
    private boolean success;

    @Schema(description = "Human-readable response message", example = "Success")
    private String message;

    @Schema(description = "Successful response payload")
    private T data;

    @Schema(description = "Error payload for failed requests")
    private ApiError error;

    @Builder.Default
    @Schema(description = "Response creation timestamp", example = "2026-03-25T12:00:00")
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

    public static <T> ApiResponse<T> error(String message, ApiError error) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .error(error)
                .build();
    }
}
