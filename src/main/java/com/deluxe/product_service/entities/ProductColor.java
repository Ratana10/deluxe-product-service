package com.deluxe.product_service.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(
        name = "product_colors",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_product_color_hex", columnNames = {"product_id", "hex"})
        },
        indexes = {
                @Index(name = "ix_product_color", columnList = "product_id")
        }
)
public class ProductColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 7)
    private String hex;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_color_product"))
    private Product product;
}
