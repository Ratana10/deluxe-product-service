package com.deluxe.product_service.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    
    private Boolean success;
    private String message;
    private String errorCode;
    private Integer status;
    private T data;
    private MetaData meta;
    private String path;
    private String timestamp;
    private String traceId;
    
    // Static factory methods for success responses
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message("Request processed successfully")
                .status(200)
                .data(data)
                .timestamp(getCurrentTimestamp())
                .traceId(generateTraceId())
                .build();
    }
    
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .status(200)
                .data(data)
                .timestamp(getCurrentTimestamp())
                .traceId(generateTraceId())
                .build();
    }
    
    public static <T> ApiResponse<T> success(T data, String message, MetaData meta) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .status(200)
                .data(data)
                .meta(meta)
                .timestamp(getCurrentTimestamp())
                .traceId(generateTraceId())
                .build();
    }
    
    // Static factory methods for error responses
    public static <T> ApiResponse<T> error(String message, int status) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .status(status)
                .timestamp(getCurrentTimestamp())
                .traceId(generateTraceId())
                .build();
    }
    
    public static <T> ApiResponse<T> error(String message, String errorCode, int status) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .status(status)
                .timestamp(getCurrentTimestamp())
                .traceId(generateTraceId())
                .build();
    }
    
    public static <T> ApiResponse<T> error(String message, String errorCode, int status, String path) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .status(status)
                .path(path)
                .timestamp(getCurrentTimestamp())
                .traceId(generateTraceId())
                .build();
    }
    
    // Utility methods
    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    }
    
    private static String generateTraceId() {
        return UUID.randomUUID().toString();
    }
}