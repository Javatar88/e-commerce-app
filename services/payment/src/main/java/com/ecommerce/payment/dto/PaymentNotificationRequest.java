package com.ecommerce.payment.dto;

import com.ecommerce.payment.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String  customerLastName,
    String customerEMail
) {
}
