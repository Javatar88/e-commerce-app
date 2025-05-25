package com.ecommerce.product.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest (

         Integer id,
         @NotNull(message = "product Name is required")
         String name,
         @NotNull(message = "product description is required")
         String description,
         @Positive(message = "availableQuantity should be positive")
         double availableQuantity,
         @Positive(message = "price should be positive")
         BigDecimal price,
         @NotNull(message = "category Id is required")
         Integer categoryId
) {
}
