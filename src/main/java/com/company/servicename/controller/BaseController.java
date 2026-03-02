package com.company.servicename.controller;

import com.company.servicename.dto.response.ApiResponse;
import com.company.servicename.dto.response.PagedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    protected <T> ResponseEntity<ApiResponse<T>> ok(String message, T data) {
        return ResponseEntity.ok(ApiResponse.success(message, data));
    }

    protected <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(message, data));
    }

    protected <T> ResponseEntity<ApiResponse<PagedResponse<T>>> paged(PagedResponse<T> data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    protected ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }
}
