package com.ecommerce.order.product;

import com.ecommerce.order.dto.PurchaseResponse;
import com.ecommerce.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProduct(List<PurchaseRequest> purchaseRequests){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity=new HttpEntity<>(purchaseRequests,httpHeaders);

        ParameterizedTypeReference<List<PurchaseResponse>> responseType=new ParameterizedTypeReference<>() {};

        ResponseEntity<List<PurchaseResponse>>  responseEntity=restTemplate.exchange(
                productUrl+"/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType);
        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("an error occurred while processing  the product purchase"+ responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }

}
