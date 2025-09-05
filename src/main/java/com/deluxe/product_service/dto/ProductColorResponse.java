package com.deluxe.product_service.dto;

import lombok.Data;

@Data
public class ProductColorResponse {
    Long id;
    Long productId;
    String hex;
}
