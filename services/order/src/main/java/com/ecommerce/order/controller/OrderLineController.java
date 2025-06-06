package com.ecommerce.order.controller;

import com.ecommerce.order.dto.OrderLineResponse;
import com.ecommerce.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController{

    private OrderLineService orderLineService;


   @GetMapping("order/{id}")
   public ResponseEntity<List<OrderLineResponse>> findById(@PathVariable("id") Integer id){

     return   ResponseEntity.ok(orderLineService.findByOrderId(id));

    }




}
