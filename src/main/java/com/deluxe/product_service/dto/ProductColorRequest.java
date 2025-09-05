package com.deluxe.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProductColorRequest {
    @NotBlank
    @Pattern(regexp = "^#?[0-9A-Fa-f]{6}$", message = "hex must be 6 hex chars, optional leading #")
    String hex;
}
