package com.ecommerce.order.kafka;


import com.ecommerce.order.dto.OrderConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final KafkaTemplate <String,OrderConfirmation> kafkaTemplate;


        public void sendOrderConfirmation(OrderConfirmation orderConFirmation){
            log.info("Sending order Confirmation");
            Message<OrderConfirmation> message= MessageBuilder
                    .withPayload(orderConFirmation)
                    .setHeader(KafkaHeaders.TOPIC, "order-topic")
                    .build();
            kafkaTemplate.send(message);

        }
}
