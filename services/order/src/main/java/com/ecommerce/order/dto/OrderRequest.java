package com.ecommerce.order.dto;

import com.ecommerce.order.entities.PaymentMethod;
import com.ecommerce.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest (

        Integer id,
        String reference,
        @Positive(message = "order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "payment method required")
        PaymentMethod paymentMethod,
        @NotNull(message = "customer Id required")
        @NotBlank(message = "customer Id should not be empty")
        String customerId,
        @NotEmpty(message = "you should at least purchase one product")
        List<PurchaseRequest> products

) {
}
