package com.ecommerce.customer.mapper;

import com.ecommerce.customer.dto.CustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import com.ecommerce.customer.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerMapper {


    public Customer toCustomer(CustomerRequest customerRequest) {
        if(Objects.isNull(customerRequest)){
            return  null;
        }
        return  Customer.builder()
                  .id(customerRequest.id())
                  .firstName(customerRequest.firstName())
                  .lastName(customerRequest.lastName())
                  .email(customerRequest.email())
                  .address(customerRequest.address())
                  .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {

     return new  CustomerResponse(customer.getId(),
             customer.getFirstName(),
             customer.getLastName(),
             customer.getEmail(),
             customer.getAddress());
    }
}
