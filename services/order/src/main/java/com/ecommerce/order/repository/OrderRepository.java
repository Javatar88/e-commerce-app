package com.ecommerce.order.repository;

import com.ecommerce.order.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order ,Integer> {


}
