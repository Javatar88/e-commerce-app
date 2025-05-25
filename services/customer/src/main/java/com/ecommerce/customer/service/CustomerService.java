package com.ecommerce.customer.service;


import com.ecommerce.customer.dto.CustomerRequest;
import com.ecommerce.customer.dto.CustomerResponse;
import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.mapper.CustomerMapper;
import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;


    public String createCustomer(CustomerRequest customerRequest) {
       var customer=customerRepository.save(customerMapper.toCustomer(customerRequest));
        return  customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest customerRequest) {

      var customer= customerRepository.findById(customerRequest.id()).orElseThrow(
              () ->  new CustomerNotFoundException("customer not found")
      );

      mergeCustomer(customer,customerRequest);
      customerRepository.save(customer);
      
    }

    private void mergeCustomer(Customer customer,  CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
             customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(Objects.nonNull(request.address())){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {

       return customerRepository.findAll().stream()
               .map(customerMapper::fromCustomer)
               .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
       return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId).map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));
    }

    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);

    }
}
