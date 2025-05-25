package com.ecommerce.order.mapper;

import com.ecommerce.order.dto.OrderRequest;


import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest) {
       return Order.builder()
               .customerId(orderRequest.customerId())
               .reference(orderRequest.reference())
               .totalAmount(orderRequest.amount())
               .paymentMethod(orderRequest.paymentMethod())
               .build();
    }

    public OrderResponse fromOrder(Order order) {
            return new OrderResponse(order.getId(),
                    order.getReference(),
                    order.getTotalAmount(),
                    order.getPaymentMethod(),
                    order.getCustomerId());

    }
}
