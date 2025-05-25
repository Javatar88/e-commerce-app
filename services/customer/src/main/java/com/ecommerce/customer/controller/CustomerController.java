package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.CustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import com.ecommerce.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest){

        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }
    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exist/{customerId}")
    public ResponseEntity<Boolean> existsById(@PathVariable ("customerId") String customerId){
        return ResponseEntity.ok(customerService.existsById(customerId));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable ("customerId") String customerId){
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable ("customerId") String customerId){
        customerService.deleteById(customerId);
        return ResponseEntity.accepted().build();
    }
}
