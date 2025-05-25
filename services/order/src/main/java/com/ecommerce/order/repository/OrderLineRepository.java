package com.ecommerce.order.repository;

import com.ecommerce.order.dto.OrderLineResponse;
import com.ecommerce.order.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {
    List<OrderLine> findByOrderId(Integer id);
}
