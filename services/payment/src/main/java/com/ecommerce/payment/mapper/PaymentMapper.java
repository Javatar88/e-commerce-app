package com.ecommerce.payment.mapper;

import com.ecommerce.payment.dto.PaymentNotificationRequest;
import com.ecommerce.payment.dto.PaymentRequest;
import com.ecommerce.payment.persistence.Payment;
import org.springframework.stereotype.Component;

@Component

public class PaymentMapper {

    public Payment toPayment(PaymentRequest paymentRequest) {

      return   Payment.builder()
              .id(paymentRequest.id())
              .orderId(paymentRequest.orderId())
              .paymentMethod(paymentRequest.paymentMethod())
              .amount(paymentRequest.amount())
              .build();
    }

    public PaymentNotificationRequest toNotificationRequest(PaymentRequest paymentRequest) {
        return new PaymentNotificationRequest(paymentRequest.orderReference(),
                paymentRequest.amount(),
                paymentRequest.paymentMethod(),
                paymentRequest.customer().firstName(),
                paymentRequest.customer().lastName(),
                paymentRequest.customer().email());
    }
}
