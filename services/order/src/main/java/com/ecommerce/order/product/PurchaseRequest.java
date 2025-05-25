package com.ecommerce.order.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotBlank(message = "product is mandatory")
        Integer productId,
        @Positive(message = "quantity is mandatory")
        double quantity

) {
}
