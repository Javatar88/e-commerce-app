package com.ecommerce.payment.service;

import com.ecommerce.payment.dto.PaymentRequest;
import com.ecommerce.payment.mapper.PaymentMapper;
import com.ecommerce.payment.persistence.Payment;
import com.ecommerce.payment.producer.NotificationProducer;
import com.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;

    private final NotificationProducer notificationProducer;

    private final PaymentMapper mapper;

    public Integer createPayment(PaymentRequest paymentRequest) {

        Payment payment=repository.save(mapper.toPayment(paymentRequest));

        notificationProducer.sendNotification(mapper.toNotificationRequest(paymentRequest));

        return payment.getId();
    }
}
