package com.ecommerce.notification.kafka.consumer.order;

import com.ecommerce.notification.kafka.consumer.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

        ) {

}
