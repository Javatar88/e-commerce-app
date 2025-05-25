package com.ecommerce.order.dto;

import com.ecommerce.order.cutomer.CustomerResponse;
import com.ecommerce.order.entities.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {

}
