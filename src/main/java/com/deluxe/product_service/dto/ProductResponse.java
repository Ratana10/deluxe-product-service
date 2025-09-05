package com.deluxe.product_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponse {
    Long id;
    String name;
    Integer categoryId;
    String description;
    BigDecimal price;
    Integer quantity;
    Integer discountPercent;
    Boolean isActive;
    private List<ProductColorResponse> productColors;

}
