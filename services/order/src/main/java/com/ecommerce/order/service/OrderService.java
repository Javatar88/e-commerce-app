package com.ecommerce.order.service;

import com.ecommerce.order.client.PaymentClient;
import com.ecommerce.order.cutomer.CustomerClient;
import com.ecommerce.order.dto.*;
import com.ecommerce.order.entities.Order;
import com.ecommerce.order.exception.BusinessException;
import com.ecommerce.order.kafka.OrderProducer;
import com.ecommerce.order.mapper.OrderMapper;
import com.ecommerce.order.product.ProductClient;
import com.ecommerce.order.product.PurchaseRequest;
import com.ecommerce.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;


    /**
     * create order
     * @param orderRequest
     * @return
     */
    public Integer createOrder(@Valid OrderRequest orderRequest) {
        //get customer  by customerId
        var customer = this.customerClient.findById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("can not create order:: no customer exist "));
        //purchase order
        var purchaseProduct= this.productClient.purchaseProduct(orderRequest.products());
        //persist order
        Order order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        //persist order lines
        for(PurchaseRequest purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity())
            );

        }

        var  paymentRequest=new PaymentRequest(orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer);


        paymentClient.requestOrderPayment(paymentRequest);
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.reference(),
                    orderRequest.amount(),
                orderRequest.paymentMethod(),
                customer,
                purchaseProduct));
        return order.getId();
    }

    public List<OrderResponse> findAll() {
      return  orderRepository
                .findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());

    }

    public OrderResponse findById(Integer id) {
        return orderRepository.
                findById(id)
                .map(orderMapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException("order not found"));
    }
}
