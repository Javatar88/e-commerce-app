package com.ecommerce.product.entity;

import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String description;
    @Column(name = "available_quantity")
    private double availableQuantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;

}
