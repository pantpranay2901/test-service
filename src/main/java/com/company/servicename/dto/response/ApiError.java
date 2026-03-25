package com.company.servicename.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "ApiError", description = "Structured error details for failed API requests")
public class ApiError {

    @Schema(description = "HTTP status code", example = "404")
    private int status;

    @Schema(description = "Short error code or reason phrase", example = "NOT_FOUND")
    private String code;

    @Schema(description = "Additional error details such as validation messages")
    private Object details;
}
