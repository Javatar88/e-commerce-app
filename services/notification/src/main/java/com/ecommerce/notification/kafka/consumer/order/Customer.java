package com.ecommerce.notification.kafka.consumer.order;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
