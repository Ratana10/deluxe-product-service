package com.deluxe.product_service.configs;

import com.deluxe.product_service.utils.ApiResponse;
import com.deluxe.product_service.utils.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(
            NotFoundException ex, HttpServletRequest req) {

        ApiResponse<Void> body = ApiResponse.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .path(req.getRequestURI())
                .timestamp(String.valueOf(Instant.now())) // if your ApiResponse has it
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // (optional) generic fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleOther(
            Exception ex, HttpServletRequest req) {

        ApiResponse<Void> body = ApiResponse.<Void>builder()
                .success(false)
                .message("Internal server error")
                .data(null)
                .path(req.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}