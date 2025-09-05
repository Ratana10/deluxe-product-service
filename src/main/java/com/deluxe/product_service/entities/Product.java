package com.deluxe.product_service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    private Integer quantity;

    @Column(name = "discount_percent")
    private Integer discountPercent;

    @Column(name = "is_active", nullable = true)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductColor> productColors = new ArrayList<>();

    public void addProductColor(ProductColor productColor) {
        productColors.add(productColor);
        productColor.setProduct(this);
    }

    public void removeProductColor(ProductColor productColor) {
        productColors.remove(productColor);
        productColor.setProduct(null);
    }

}
//
//    @OneToMany(
//            mappedBy = "product",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY
//    )
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private List<ProductColor> productColors = new ArrayList<>();
