package com.ecommerce.order.mapper;


import com.ecommerce.order.dto.OrderLineRequest;
import com.ecommerce.order.dto.OrderLineResponse;
import com.ecommerce.order.entities.Order;
import com.ecommerce.order.entities.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {


    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .order(Order.builder().id(orderLineRequest.orderId()).build())
                .productId(orderLineRequest.productId())
                .quantity(orderLineRequest.quantity())
                .build();


    }

    public OrderLineResponse  toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(),
                orderLine.getQuantity());
    }
}
