package com.ecommerce.order.service;

import com.ecommerce.order.dto.OrderLineRequest;
import com.ecommerce.order.dto.OrderLineResponse;
import com.ecommerce.order.mapper.OrderLineMapper;
import com.ecommerce.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
          var orderLine= orderLineMapper.toOrderLine(orderLineRequest);
          return  orderLineRepository.save(orderLine).getId();
    }

    public List<OrderLineResponse> findByOrderId(Integer id){
        return orderLineRepository.findByOrderId(id)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
