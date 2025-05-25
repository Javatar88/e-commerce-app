package com.ecommerce.notification.kafka.consumer;

import com.ecommerce.notification.email.EmailService;
import com.ecommerce.notification.repository.NotificationRepository;
import com.ecommerce.notification.entties.Notification;
import com.ecommerce.notification.kafka.consumer.order.OrderConfirmation;
import com.ecommerce.notification.kafka.consumer.payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.ecommerce.notification.enums.NotificationType.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("consuming message from payment topic {}" ,paymentConfirmation);

        notificationRepository.save(Notification.builder()
                .notificationType(PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build());
        var customerName=paymentConfirmation.customerFirstName()+ " "+paymentConfirmation.customerLastName();

        emailService.sendPaymentSuccessEmail(paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference());
    }
    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("consuming message from order topic {}" ,orderConfirmation);

        notificationRepository.save(Notification.builder()
                .notificationType(ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());
        var customerName=orderConfirmation.customer().firstName()+ " "+orderConfirmation.customer().lastName();

        emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products());
    }

}
