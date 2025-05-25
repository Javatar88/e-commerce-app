package com.ecommerce.order.dto;

import com.ecommerce.order.cutomer.CustomerResponse;
import com.ecommerce.order.entities.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
