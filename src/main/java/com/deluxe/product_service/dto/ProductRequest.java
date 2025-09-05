package com.deluxe.product_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    String name;

    @NotNull(message = "Category is required")
    Integer categoryId;

    String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    Integer quantity;

    @Min(value = 0, message = "Discount percent cannot be negative")
    @Max(value = 100, message = "Discount percent cannot exceed 100")
    Integer discountPercent;

    Boolean isActive;
}
